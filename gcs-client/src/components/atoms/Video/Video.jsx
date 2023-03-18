import styled from "styled-components";

const StyledVideoContainer = styled.div`
  height: 30%;
  background: #282c34;
`
const Video = ({children, ...props}) => {
  return (
    <StyledVideoContainer>
      {children}
    </StyledVideoContainer>
  )
}

export default Video;