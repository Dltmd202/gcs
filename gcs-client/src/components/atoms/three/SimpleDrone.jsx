import { SphereGeometry, BoxGeometry } from 'three';
import React, {useEffect, useRef, useState} from "react";
import { useFrame } from "@react-three/fiber";
import { Euler } from 'three';
import {OSphereGeometry} from "three/addons/libs/OimoPhysics";

function SimpleDrone({
                       agentObj,
                       onClick,
                       focused=false,
                       ...props
                    }) {
  const meshRef = useRef()
  const [hovered, hover] = useState(false);
  const [clicked, click] = useState(false)
  const {active, angle, rtk, color, complementaryColor, led} = agentObj
  const head = new BoxGeometry(0.1, 0.4, 0.4);
  const body = new SphereGeometry(0.5, 16, 16);

  const getColor = () => {
    if(led.r !== -1){
      var hexR = led.r.toString(16).padStart(2, '0');
      var hexG = led.g.toString(16).padStart(2, '0');
      var hexB = led.b.toString(16).padStart(2, '0');
      return '#' + hexR + hexG + hexB;
    } else {
      return "#000000";
    }

  }

  return (
    <group
      visible={active}
      position={[rtk.x, -rtk.z, rtk.y]}
      scale={1}
      onClick={onClick}
      onPointerOver={(event) => hover(true)}
      onPointerOut={(event) => hover(false)}
      rotation={[angle.roll, angle.yaw, angle.pitch]}
    >
      <mesh
        geometry={body}
        position={[0, 0.25, 0]}
      >
        <meshPhongMaterial
          color={hovered || focused ? complementaryColor : getColor()}
          transparent={true}
          opacity={0.8}
        />
      </mesh>
      <mesh
        scale={0.4}
        geometry={head}
        position={[0.5, 0.25, 0]}
      >
        <meshPhongMaterial
          color={hovered || focused ? complementaryColor : getColor()}
          emissive={hovered || focused ? getColor() : complementaryColor}
        />
      </mesh>
    </group>
  )
}

export default SimpleDrone;