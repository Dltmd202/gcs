import * as THREE from 'three';
import React, {useRef, useState} from "react";
import { useFrame } from "@react-three/fiber";
import { Euler } from 'three';

function SimplePathDrone({
                      agentObj,
                      onClick,
                      ...props
                    }) {
  const meshRef = useRef()
  const droneGeometry = new THREE.BoxGeometry(0.5, 0.3, 1);
  const [hovered, hover] = useState(false)
  const [clicked, click] = useState(false)
  const {active, angle, ned} = agentObj


  useFrame((state, delta) => {
    const mesh = meshRef.current;
    const eular = new Euler(angle.roll, angle.yaw, angle.pitch);
    mesh.rotation.setFromVector3(eular);
  })

  droneGeometry.translate(0, 0.15, 0)

  return (
    <mesh
      visible={active}
      position={[ned.x, ned.z, ned.y]}
      ref={meshRef}
      scale={clicked ? 0.8 : 0.5}
      onClick={onClick}
      onPointerOver={(event) => hover(true)}
      onPointerOut={(event) => hover(false)}
      geometry={droneGeometry}
    >
      <axesHelper scale={2} />
      <meshStandardMaterial
        color={agentObj.color}
        transparent={true}
        opacity={0.6}
      />
    </mesh>
  )
}

export default SimplePathDrone;