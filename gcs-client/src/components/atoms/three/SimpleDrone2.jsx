import * as THREE from 'three';
import React, {useRef, useState} from "react";

function SimpleDrone2({
                      agentObj,
                      onClick,
                      ...props
                    }) {
  const meshRef = useRef()
  const droneGeometry = new THREE.BoxGeometry(0.5, 0.3, 1);
  const [hovered, hover] = useState(false)
  const [clicked, click] = useState(false)
  const {active, angle, ned, color, complementaryColor} = agentObj

  droneGeometry.translate(0, 0.15, 0)

  return (
    <group
      visible={active}
      position={[ned.x, -ned.z, ned.y]}
      ref={meshRef}
      scale={clicked ? 0.8 : 0.5}
      onClick={onClick}
      onPointerOver={(event) => hover(true)}
      onPointerOut={(event) => hover(false)}
      rotation={[angle.roll, angle.yaw, angle.pitch]}
    >
      <axesHelper scale={2} />
      <mesh position={[0, 0.05, 0]}>
        {/*본체*/}
        <boxGeometry args={[0.5, 0.1, 0.25]}/>
        <meshBasicMaterial color={hovered ? complementaryColor : color} />
      </mesh>
      <mesh position={[0, 0.1, 0]} rotation={[0, Math.PI / 4, 0]}>
        {/*막대*/}
        <boxGeometry args={[1, 0.03, 0.03]}/>
        <meshBasicMaterial
          color={hovered ? complementaryColor : color}
          transparent={true}
          opacity={0.8}
        />
      </mesh>
      <mesh position={[0, 0.1, 0]} rotation={[0, -Math.PI / 4, 0]}>
        {/*막대*/}
        <boxGeometry args={[1, 0.03, 0.03]}/>
        <meshBasicMaterial
          color={hovered ? complementaryColor : color}
          transparent={true}
          opacity={0.8}
        />
      </mesh>
      <mesh position={[0.35, 0.1, 0.35]} rotation={[Math.PI / 2, 0, 0, 16]}>
        {/*프로펠러*/}
        <cylinderGeometry args={[0.2, 0.1, 0.01]}/>
        <meshBasicMaterial
          color={hovered ? complementaryColor : color}
          transparent={true}
          opacity={0.1}
        />
      </mesh>
      <mesh position={[-0.35, 0.1, 0.35]} rotation={[Math.PI / 2, 0, 0, 16]}>
        {/*프로펠러*/}
        <cylinderGeometry args={[0.2, 0.1, 0.01]}/>
        <meshBasicMaterial
          color={hovered ? complementaryColor : color}
          transparent={true}
          opacity={0.1}
        />
      </mesh>
      <mesh position={[0.35, 0.1, -0.35]} rotation={[Math.PI / 2, 0, 0, 16]}>
        {/*프로펠러*/}
        <cylinderGeometry args={[0.2, 0.1, 0.01]}/>
        <meshBasicMaterial
          color={hovered ? complementaryColor : color}
          transparent={true}
          opacity={0.1}
        />
      </mesh>
      <mesh position={[-0.35, 0.1, -0.35]} rotation={[Math.PI / 2, 0, 0, 16]}>
        {/*프로펠러*/}
        <cylinderGeometry args={[0.2, 0.1, 0.01]}/>
        <meshBasicMaterial
          color={hovered ? complementaryColor : color}
          transparent={true}
          opacity={0.1}
        />
      </mesh>
    </group>
  )
}

export default SimpleDrone2;