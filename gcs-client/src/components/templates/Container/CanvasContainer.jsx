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
          distance={450000}
          sunPosition={[0, 1, 0]}
          inclination={0}
          rayleigh={0.1}
          turbidity={10}
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