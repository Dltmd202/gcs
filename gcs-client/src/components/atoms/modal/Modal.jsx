import styled from "styled-components";
import Colors from "../../../styles/colors";

const Modal = ({
                 children,
                 style,
                 onClose,
                 ...props
}) => {
  return (
    <ModalContainer
      style={style}
      onClick={onClose}
    >
      {children}
    </ModalContainer>
  )
}

const ModalContainer = styled.div`
  box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
  border-radius: 0.3em;
  background: ${Colors.backgroundSidebar};
  z-index: 1;
`

export default Modal;