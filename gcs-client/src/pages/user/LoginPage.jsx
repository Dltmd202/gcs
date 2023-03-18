import React from "react";
import * as Yup from 'yup';
import {useNavigate} from "react-router-dom";
import {useDispatch} from "react-redux";
import {useFormik} from "formik";
import userApi from "../../api/user";
import {fetchUser} from "../../store/user";
import Swal from "sweetalert2";
import Colors from "../../styles/colors";
import UtilContainer from "../../components/templates/Container/UtilContainer";
import styled from "styled-components";
import {FontSize, FontWeight} from "../../styles/font";
import Button from "../../components/atoms/Button/Button";
import {Input} from "../../components/atoms/Input/Inputs";
import Spinner from "../../components/atoms/Spinner/Spinner";

const initialValues = {
  username: '',
  password: '',
};

const validationSchema = Yup.object().shape({
  username: Yup.string()
    .strict(true)
    .required('아이디를 입력해주세요.'),
  password: Yup.string()
    .strict(true)
    .required('비밀번호를 입력해주세요.'),
});


const LoginPage = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const {
    errors,
    handleBlur,
    handleChange,
    handleSubmit,
    isSubmitting,
    touched,
    values,
  } = useFormik({
    initialValues,
    validationSchema,
    onSubmit: async (values, formikHelper) => {
      try {
        const loginResponse = await userApi.signIn(values);
        const token = loginResponse.data.response.token;
        const role = loginResponse.data.response.roles;
        localStorage.setItem('token', token);
        localStorage.setItem('role', role)
        dispatch(fetchUser());
        formikHelper.setStatus({ success: true });
        formikHelper.setSubmitting(false);
        Swal.fire({
          icon: 'success',
          text: '로그인에 성공하였습니다.',
          showConfirmButton: false,
          timer: 1500,
        }).then(() => {
          navigate(role == 'ADMIN' ? '/load' : '/gcs/three');
        });
      } catch (error) {
        Swal.fire({
          icon: 'error',
          text: `로그인에 실패하였습니다. 로그인 정보를 확인해주세요.`,
          confirmButtonColor: Colors.point,
        });
      }
    },
  });

  return (
    <StyledContainer>
      <HeadText>로그인</HeadText>
      <SignInForm onSubmit={handleSubmit}>
        <Label htmlFor="username">아이디</Label>
        <Input
          id="username"
          name="username"
          type="username"
          placeholder="아이디"
          onChange={handleChange}
          onBlur={handleBlur}
          value={values.email}
        />
        <GuideText>{touched.username && errors.username}&nbsp;</GuideText>
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
        <StyledButton type="submit" disabled={isSubmitting}>
          로그인
        </StyledButton>
        <StyledButton
          type="button"
          colorType="white"
          onClick={() => {
            navigate('/join');
          }}
        >
          회원가입
        </StyledButton>
      </SignInForm>
      {isSubmitting && <Spinner />}
    </StyledContainer>
  )
}

const StyledContainer = styled(UtilContainer)`
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

export default LoginPage;