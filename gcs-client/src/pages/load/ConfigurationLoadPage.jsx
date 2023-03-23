import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import styled from "styled-components";
import UtilContainer from "../../components/templates/Container/UtilContainer";
import { FontWeight } from "../../styles/font";
import Colors from "../../styles/colors";
import FileInput from "../../components/atoms/Input/FileInput";
import contextApi from "../../api/context";
import ContextSelectorItem from "../../components/molecules/contextFile/contextFile";
import { Media } from "../../styles/media";
import { useDispatch } from "react-redux";
import { holdContext } from "../../store/context";
import { fetchUser } from "../../store/user";

const ConfigurationLoadPage = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [contextFileList, setContextFileList] = useState([]);
  const [isLoading, setLoading] = useState(false);
  const token = localStorage.getItem("token");

  const getMyConfiguration = async () => {
    try{
      if(!token) return;
      setLoading(true);

      const contextList = await contextApi.getMyContextList();

      setContextFileList(contextList.data.response);
      setLoading(false);
    } catch (e){

    }
  }

  useEffect(() => {
    dispatch(fetchUser())
    getMyConfiguration();
  }, []);

  const handleChangeFileInput = async (e) => {
    e.preventDefault();
    const values = {file: e.target.files[0]}
    const contextRes = await contextApi.postContextConfigurationFile(values);
    if(contextRes.status === 201){
      const contextId = contextRes.data.response;
      dispatch(holdContext(contextId));
      navigate(`/gcs/three`)
    } else if(contextRes.status === 400){
      alert(contextRes.error.message);
    }
  }

  const onClickContextFile = (e, id) => {
    console.log("fuck");
    console.log(id);
    dispatch(holdContext(id));

    navigate(`/gcs/three`)
  }

  return(
    <StyledContainer>
      <HeadText>Configuration 로드</HeadText>
      {isLoading && contextFileList.length !== 0 ? null : (
        <StyledGridBox>
          {contextFileList.map((contextFile, i) => (
            <ContextSelectorItem
              key={i}
              onClick={(e) => onClickContextFile(e, contextFile['contextId'])}
              contextFileObject={contextFile}
            />
          ))}
        </StyledGridBox>
      )}
      <FileInput
        id={"conf_loader"}
        inputChange={handleChangeFileInput}
        inputName="file"
        inputType="file"
        accept={".conf"}
      >
        Configuration 불러오기
      </FileInput>
    </StyledContainer>
  )
}

const StyledGridBox = styled.div`
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  justify-content: center;
  gap: 32px 56px;
  padding: 40px 0;
  width: 100%;

  @media ${Media.sm} {
    grid-template-columns: repeat(3, 1fr);
    gap: 10px 14px;
    padding: 20px 0;

    @media (max-width: 480px) {
      grid-template-columns: repeat(2, 1fr);
    }
  }
`

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


export default ConfigurationLoadPage;