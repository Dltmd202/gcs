import { useNavigate } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import { useEffect } from 'react';
import Swal from 'sweetalert2';
import {fetchUser, user as userStore} from "../../store/user";
import Colors from "../../styles/colors";
import {FontSize, FontWeight} from "../../styles/font";
import {Media} from "../../styles/media";
import styled from "styled-components";
import Button from "../../components/atoms/Button/Button";
import Spinner from "../../components/atoms/Spinner/Spinner";

const UserPage = () => {
  const navigator = useNavigate();
  const dispatch = useDispatch();
  const { data: user, loading } = useSelector((state) => state.user);

  useEffect(() => {
    dispatch(fetchUser());
  }, [dispatch]);

  const handleClickLogoutButton = () => {
    Swal.fire({
      icon: 'question',
      text: '정말 로그아웃 하시겠습니까?',
      showCancelButton: true,
      confirmButtonColor: Colors.point,
      cancelButtonColor: Colors.functionNegative,
      confirmButtonText: '네',
      cancelButtonText: '아니오',
    }).then((result) => {
      if (result.isConfirmed) {
        localStorage.removeItem("token");
        dispatch(userStore.actions.deleteUser());
        navigator('/');
        Swal.fire({
          icon: 'success',
          text: '👋🏻 로그아웃 되었습니다.',
          showConfirmButton: false,
          timer: 1500,
        });
      }
    });
  };

  return (
    <StyledContainer navBar>
      <HeadText>프로필</HeadText>
      <ContentContainer>
        <FieldWrapper>
          <FieldText>이름</FieldText>
          <Text>{user ? user.name : ''}</Text>
        </FieldWrapper>
        <FieldWrapper>
          <FieldText>권한</FieldText>
          <Text>{user ? user.roles : ''}</Text>
        </FieldWrapper>
      </ContentContainer>
      <ButtonWrapper>
        <Button
          Content={"수정하기"}
          ClickFunc={() => {
            navigator(`/mypage/edit`);
          }}
        />
        <Button
          Content={"로그아웃"}
          ClickFunc={handleClickLogoutButton}/>
      </ButtonWrapper>
      {loading && <Spinner />}
    </StyledContainer>
  );
};

const StyledContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: 100%;
`;

const ContentContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 1rem;
  width: 100%;
  margin-bottom: 80px;
`;

const HeadText = styled.h1`
  margin-bottom: 80px;
  font-size: 24px;
  font-weight: ${FontWeight.bold};
  color: ${Colors.textPrimary};
`;

const FieldText = styled.p`
  margin: 1rem 0;
  color: ${Colors.textSecondary};

  @media ${Media.sm} {
    font-size: ${FontSize.small};
  }
  @media ${Media.md} {
    font-size: ${FontSize.base};
  }
  @media ${Media.lg} {
    font-size: ${FontSize.base};
  }
`;

const Text = styled.p`
  color: ${Colors.textPrimary};

  @media ${Media.sm} {
    font-size: ${FontSize.medium};
  }
  @media ${Media.md} {
    font-size: ${FontSize.large};
  }
  @media ${Media.lg} {
    font-size: ${FontSize.large};
  }
`;

const FieldWrapper = styled.div`
  display: flex;
  flex-direction: column;
`;

const ButtonWrapper = styled.div`
  display: flex;
  flex-direction: column;
  gap: 2rem;
  width: 100%;
  margin-bottom: 2rem;
`;

export default UserPage;
