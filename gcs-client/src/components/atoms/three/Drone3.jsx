import React, {useState} from "react";
import drone from "../../assets/obj/drone.glb"
import GltfModel from "./GltfModel";

const Drone = (
  agentObj,
  ned,
  ...props
) => {
  const [hovered, hover] = useState(false)
  const [clicked, click] = useState(false)
  const [obj, setObj] = useState();
  const {active} = agentObj


  return (
    <mesh
      castShadow
      position={[0, 1, 2]}
      // scale={[0.05, 0.05, 0.05]}
      rotation={[0, 8, 0]}
    >
      <GltfModel modelPath={drone} />
      <axesHelper scale={2} />
    </mesh>
  )
}

export default Drone;