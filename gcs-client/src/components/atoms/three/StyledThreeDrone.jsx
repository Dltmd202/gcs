import React, {useEffect, useRef, useState} from "react";
import * as THREE from 'three';
import drone from "../../assets/obj/drone.glb"
import {GLTFLoader} from "three/examples/jsm/loaders/GLTFLoader";
import {useLoader} from "@react-three/fiber";
import {gltfLoader} from "./GltfLoader";

const StyledThreeDrone = ({
                            agentObj,
                            onClick,
                            ...props
}) => {
  const gltfLoader = useRef(new GLTFLoader());
  const gltf = useLoader(GLTFLoader, drone);
  const [loading, setLoading] = useState(false);
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

  useEffect(() => {
    setLoading(true);
    gltfLoader.current.load(drone, (gltf) => {
      gltf.scene.traverse((child) => {
        if (child instanceof THREE.Mesh) {
          child.material = material;
        }
      });
      setObj(gltf.scene);
    });
    setLoading(false);
  }, []);

  gltf.scene.traverse((child) => {
    if (child instanceof THREE.Mesh) {
      child.material = material;
    }
  });

  const onHover = (event) => {
    hover(true)
  }

  const onHout = (event) => {
    hover(false)
  }

  console.log(obj);

  return (loading &&
    <mesh
      position={[ned.x, ned.z, ned.y]}
      scale={[0.05, 0.05, 0.05]}
      onPointerOver={onHover}
      onPointerOut={onHout}
      onClick={onClick}
      rotation={[angle.roll, angle.yaw, angle.pitch]}
    >
      <primitive object={obj} />
      <axesHelper scale={2} />
    </mesh>
  )
}

export default StyledThreeDrone;