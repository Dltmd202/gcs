import { useEffect, useState } from 'react';
import Portal from "../Portal/Portal";
import styled from "styled-components";
import Colors from "../../../styles/colors";
import {RingLoader} from "react-spinners";


const Spinner = ({ ...props }) => {
  const [visible, setVisible] = useState(false);

  useEffect(() => {
    const timer = setTimeout(() => {
      setVisible(true);
    }, 500);

    return () => {
      timer && clearTimeout(timer);
    };
  }, []);

  return (
    <Portal>
      <BackgroundDim visible={visible}>
        <RingLoader color={Colors.main} {...props} />
      </BackgroundDim>
    </Portal>
  );
};

const BackgroundDim = styled.div`
  display: flex;
  visibility: ${({ visible }) => (visible ? 'visible' : 'hidden')};
  justify-content: center;
  align-items: center;
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 1000;
`;

export default Spinner;
