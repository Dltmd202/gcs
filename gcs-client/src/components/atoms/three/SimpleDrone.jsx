import {SphereGeometry, BoxGeometry, BufferGeometry, BufferAttribute, TetrahedronGeometry, ConeGeometry} from 'three';
import React, {memo, useEffect, useMemo, useRef, useState} from "react";
import { Euler } from 'three';
import {agentStatusMask} from "../../../module/coordinate/agentStatus";
import Colors from "../../../styles/colors";

function SimpleDrone({
                       agentObj,
                       onClick,
                       focused=false,
                       ...props
                     }) {
  const meshRef = useRef();
  const [hovered, hover] = useState(false);
  const { active, angle, rtk, color, complementaryColor, led, status, sysid } = agentObj;
  const [abnormal, setAbnormal] = useState(false);

  const [unFixed, setUnFixed] = useState(false);
  const [age1, setAge1] = useState(false);
  const [age2, setAge2] = useState(false);

  const normalBody = useMemo(() => new SphereGeometry(0.5, 16, 16), []);
  const triangleBody = useMemo(() => new ConeGeometry(0.5, 1, 3), []);


  useEffect(() => {
    setUnFixed((status & agentStatusMask[9].mask) === 0);
    setAge1((status & agentStatusMask[25].mask) !== 0);
    setAge2((status & agentStatusMask[26].mask) !== 0);
  }, [status]);

  useEffect(() => {
    setAbnormal(unFixed || age2);
    console.log(sysid, unFixed, age2, abnormal)
  }, [unFixed, age2]);

  const getColor = () => {
    if(focused || hovered){
      return complementaryColor;
    }
    if (led.r !== -1) {
      var hexR = led.r.toString(16).padStart(2, '0');
      var hexG = led.g.toString(16).padStart(2, '0');
      var hexB = led.b.toString(16).padStart(2, '0');
      return '#' + hexR + hexG + hexB;
    } else {
      return Colors.black;
    }
  };

  const eulerRotation = useMemo(() => new Euler(angle.roll, angle.yaw, angle.pitch), [angle]);

  return (
    <group
      visible={active}
      position={[rtk.x, -rtk.z, rtk.y]}
      scale={1}
      onClick={onClick}
      onPointerOver={(event) => hover(true)}
      onPointerOut={(event) => hover(false)}
      rotation={eulerRotation}
    >
      <mesh
        geometry={abnormal ? triangleBody : normalBody}
        rotation={abnormal ? [Math.PI, 0, 0] : [0, 0, 0]}
        position={[0, 0.25, 0]}
      >
        <meshPhongMaterial
          color={hovered || focused ? complementaryColor : getColor()}
          transparent={true}
          opacity={0.8}
        />
      </mesh>
    </group>
  );
}

export default memo(SimpleDrone);