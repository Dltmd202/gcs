import React, {useEffect, useState} from "react";
import * as THREE from 'three';
import {useLoader} from "@react-three/fiber";
import drone from "../../assets/obj/drone.glb"
import {gltfLoader} from "./GltfLoader";
import {GLTFLoader} from "three/examples/jsm/loaders/GLTFLoader";

const StyledThreePathDrone = ({
                            agentObj,
                            onClick,
                            ...props
                          }) => {
  const [gltf1, gltf,] = useLoader(GLTFLoader, [drone, drone]);
  const [hovered, hover] = useState(false)
  const [clicked, click] = useState(false)
  const [obj, setObj] = useState();
  const {
    active,
    angle,
    color,
    complementaryColor,
    ned,
  } = agentObj
  const material = new THREE.MeshStandardMaterial({ color: hovered ? complementaryColor : color });
  material.transparent = true;
  material.opacity = 0.6;

  gltf.scene.traverse((child) => {
    if (child instanceof THREE.Mesh) {
      child.material = material;
    }
  });
  console.log(gltf);
  const onHover = (event) => {
    hover(true)
  }

  const onHout = (event) => {
    hover(false)
  }
  console.log(agentObj);
  return (
    <mesh
      castShadow
      position={[ned.x, ned.z, ned.y]}
      scale={[0.05, 0.05, 0.05]}
      onPointerOver={onHover}
      onPointerOut={onHout}
      onClick={onClick}
      rotation={[angle.roll, angle.yaw, angle.pitch]}
    >
      <primitive object={gltf.scene} />
      <axesHelper scale={2} />
    </mesh>
  )
}

export default StyledThreePathDrone;