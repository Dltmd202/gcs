import * as Yup from 'yup';
import {useNavigate} from "react-router-dom";
import {useState} from "react";
import Swal from "sweetalert2";
import userApi from "../../api/user";
import Colors from "../../styles/colors";
import {useFormik} from "formik";
import Container from "../../components/templates/Container/UtilContainer";
import styled, {css} from "styled-components";
import Spinner from "../../components/atoms/Spinner/Spinner";
import {FontSize, FontWeight} from "../../styles/font";
import {Input} from "../../components/atoms/Input/Inputs";
import Button from "../../components/atoms/Button/Button";

const initialValues = {
  name: '',
  username: '',
  password: '',
  checkPassword: '',
};

const validationSchema = Yup.object().shape({
  name: Yup.string()
    .strict(true)
    .max(64)
    .required("이름을 입력해주세요"),
  username: Yup.string()
    .strict(true)
    .required("아이디를 입력해주세요"),
  password: Yup.string()
    .strict(true)
    .required('비밀번호를 입력해주세요.'),
  checkPassword: Yup.string()
    .strict(true)
    .required("비밀번호를 확인해주세요"),
})

const JoinPage = () => {
  const navigate = useNavigate();
  const [isValidUsername, setValidUsername] = useState(false);
  const {
    errors,
    handleBlur,
    handleChange,
    handleSubmit,
    setFieldValue,
    isSubmitting,
    touched,
    values,
  } = useFormik({
    initialValues,
    validationSchema,
    onSubmit: async (values, formikHelper) => {
      if (!isValidUsername) {
        Swal.fire({
          icon: 'error',
          title: '아이디 중복 확인을 해주세요.',
        });
        return;
      }

      try {
        await userApi.signUp(values);
        formikHelper.resetForm();
        formikHelper.setStatus({ success: true });
        formikHelper.setSubmitting(false);
        Swal.fire({
          icon: 'success',
          text: '계정 생성에 성공하였습니다.',
          confirmButtonColor: Colors.point,
        }).then(() => {
          navigate('/');
        });
      } catch (error) {
        console.log(error);
        Swal.fire({
          icon: 'error',
          text: error.data.error.message,
          confirmButtonColor: Colors.point,
        });
      }
    },
  });

  const handleChangeEmailInput = (e) => {
    isValidUsername && setValidUsername(false);
    handleChange(e);
  };

  const transformBlur = (e) => {
    setFieldValue(e.target.name, e.target.value.trim());
    handleBlur(e);
  };

  const checkValidUsername = async () => {
    if (errors.username) {
      Swal.fire({
        icon: 'error',
        text: `사용할 수 없는 아이디입니다.`,
        confirmButtonColor: Colors.point,
      });
      return;
    }
    const response = await userApi.validateUsername(values.username);
    console.log(response);
    const isValidUsername = response.data.response;

    if (isValidUsername) {
      Swal.fire({
        icon: 'success',
        text: `사용가능한 아이디입니다.`,
        confirmButtonColor: Colors.point,
      });
      setValidUsername(true);
    } else {
      Swal.fire({
        icon: 'error',
        text: `이미 존재하는 아이디입니다. 다른 아이디를 사용해주세요!`,
        confirmButtonColor: Colors.point,
      });
    }
  };

  const handleChangeUsernameInput = (e) => {
    isValidUsername && setValidUsername(false);
    handleChange(e);
  };

  return (
    <StyledContainer>
      <HeadText>Join</HeadText>
      <SignInForm onSubmit={handleSubmit}>
        <Label htmlFor="username">아이디</Label>
        <Divider>
          <Input
            id="username"
            name="username"
            type="string"
            placeholder="아이디"
            onChange={handleChangeEmailInput}
            onBlur={transformBlur}
            value={values.username}
          />
          <UsernameCheckButton
            type="button"
            disabled={isValidUsername}
            onClick={checkValidUsername}
          >
            {isValidUsername ? '사용가능' : '중복확인'}
          </UsernameCheckButton>
        </Divider>
        <GuideText>{touched.username && errors.username}&nbsp;</GuideText>
        <Label htmlFor="name">이름</Label>
        <Input
          id="name"
          name="name"
          type="text"
          placeholder="이름"
          onChange={handleChange}
          onBlur={handleBlur}
          value={values.name}
        />
        <GuideText>{touched.name && errors.name}&nbsp;</GuideText>
        <Label htmlFor="password">비밀번호</Label>
        <Input
          id="password"
          name="password"
          type="password"
          placeholder="비밀번호"
          onChange={handleChange}
          onBlur={handleBlur}
          value={values.password}
        />
        <GuideText>{touched.password && errors.password}&nbsp;</GuideText>
        <Label htmlFor="checkPassword">비밀번호 확인</Label>
        <Input
          id="checkPassword"
          name="checkPassword"
          type="password"
          placeholder="비밀번호 확인"
          onChange={handleChange}
          onBlur={handleBlur}
          value={values.checkPassword}
        />
        <GuideText>
          {touched.checkPassword && errors.checkPassword}&nbsp;
        </GuideText>
        <StyledButton type="submit" disabled={isSubmitting}>
          가입하기
        </StyledButton>
        <StyledButton
          colorType="white"
          type="button"
          onClick={() => {
            navigate("/");
          }}
        >
          취소하기
        </StyledButton>
      </SignInForm>
      {isSubmitting && <Spinner />}
    </StyledContainer>
  );
}

const StyledContainer = styled(Container)`
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: 100%;
`;

const HeadText = styled.h1`
  margin-bottom: 80px;
  font-size: 24px;
  font-weight: ${FontWeight.bold};
  color: ${Colors.textPrimary};
`;

const SignInForm = styled.form`
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 100%;
  margin-bottom: 1rem;
`;

const Label = styled.label`
  display: inline-block;
  margin-bottom: 0.5rem;
  font-size: ${FontSize.base};
  color: ${Colors.textSecondary};
`;

const GuideText = styled.p`
  margin: 1rem 0;
  color: ${Colors.functionNegative};
`;

const StyledButton = styled(Button)`
  margin: 1rem 0;
`;

const UsernameCheckButton = styled(Button)`
  margin-left: 1rem;
  width: 120px;

  ${({ disabled }) => css`
    background-color: ${disabled && Colors.pointLight};

    @media (hover: hover) {
      :hover {
        color: ${disabled && Colors.textQuaternary};
        background-color: ${disabled && Colors.pointLight};
      }
    }

    :active {
      background-color: ${disabled && Colors.pointLight};
    }
  `}
`;

const Divider = styled.div`
  display: flex;
  justify-content: center;
`;

export default JoinPage;