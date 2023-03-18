import React from "react";
import {GLTFLoader} from "three/examples/jsm/loaders/GLTFLoader";
import {useLoader} from "@react-three/fiber";
const GltfModel = ({ modelPath }) => {
  const gltf = useLoader(GLTFLoader, modelPath);
  return <primitive object={gltf.scene} />;
};

export default GltfModel;