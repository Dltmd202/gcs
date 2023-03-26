import styled from "styled-components";
import {OrbitControls, PerspectiveCamera, Sky} from "@react-three/drei";
import React from "react";
import {Canvas} from "@react-three/fiber";
import {Physics} from "@react-three/cannon";

const CanvasContainer = ({
                           children,
                           cameraRef,
                           controlRef,
                           ...props
}) => {

  return (
    <ThreeContainer>
      <Canvas>
        <PerspectiveCamera
          ref={cameraRef}
          fov={75}
          aspect={window.innerWidth / window.innerHeight}
          near={0.1}
          far={1000}
          position={[0, 0, 5]}
        />
        <OrbitControls
          ref={controlRef}
          enableDamping
          dampingFactor={0.5}
          autoRoutate={true}
          minDistance={2}
          maxDistance={200}
          maxPolarAngle={Math.PI / 2 + 0.1}
          args={[cameraRef.current]}
        />
        <Sky
          distance={1000} // 하늘과 지표면 사이의 거리
          sunPosition={[0, 1, 0]} // 태양의 위치
          turbidity={0} // 구름의 얼마나 빽빽한지를 나타내는 값
          rayleigh={0.01} // 하늘색을 나타내는 값
          // brightness={0.2} // 하늘 밝기 조절 (기본값 1)
          inclination={0.4} // 태양 고도 (기본값 0.49)
          azimuth={180} // 태양 방위각 (기본값 0.25)
          elevation={0}
          exposure={0.3}
          stars={true} // 별 보이기 여부
        />
        <ambientLight intensity={0.5} />
        <axesHelper scale={1000} args={[5]}/>
        <gridHelper args={[1000, 200]} />
        <Physics>
          {children}
        </Physics>
      </Canvas>
    </ThreeContainer>
  )
}

const ThreeContainer = styled.div`
  display: flex;
  height: 100%;
`;

export default CanvasContainer;