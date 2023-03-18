import React, {useState, useMemo} from "react";
import {OBJLoader} from "three/examples/jsm/loaders/OBJLoader";
import {Mesh} from "three";

const ObjectModel = ({ modelPath }) => {
  const [obj, setObj] = useState();
  useMemo(() => new OBJLoader().load(modelPath, setObj), [modelPath]);
  if (obj) {
    obj.castShadow = true;
    obj.traverse((children) => {
      if (children instanceof Mesh) {
        children.castShadow = true;
      }
    });
  }
  return obj ? <primitive object={obj} /> : null;
};

export default ObjectModel;