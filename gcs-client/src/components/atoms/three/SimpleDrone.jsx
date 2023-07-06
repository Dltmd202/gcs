import {SphereGeometry, BoxGeometry, BufferGeometry, BufferAttribute, TetrahedronGeometry, ConeGeometry} from 'three';
import React, {memo, useEffect, useMemo, useRef, useState} from "react";
import { Euler } from 'three';
import {agentStatusMask} from "../../../module/coordinate/agentStatus";
import Colors from "../../../styles/colors";

function SimpleDrone({
                       agentObj,
                       onClick,
                       focused=false,
                       setFlyable,
                       ...props
                     }) {
  const meshRef = useRef();
  const [hovered, hover] = useState(false);
  const { active, angle, rtk, color, complementaryColor, led, status, sysid } = agentObj;
  const [abnormal, setAbnormal] = useState(false);

  const [unFixed, setUnFixed] = useState(false);
  const [age1, setAge1] = useState(false);
  const [age2, setAge2] = useState(false);
  const [ledColor, setLedColor] = useState(Colors.black);

  const normalBody = useMemo(() => new SphereGeometry(0.5, 16, 16), []);
  const triangleBody = useMemo(() => new ConeGeometry(0.5, 1, 3), []);
  const selector = useMemo(() => new ConeGeometry(0.15, 0.3, 30), []);


  useEffect(() => {
    setUnFixed((status & agentStatusMask[9].mask) === 0);
    setAge1((status & agentStatusMask[25].mask) !== 0);
    setAge2((status & agentStatusMask[26].mask) !== 0);
  }, [status]);

  useEffect(() => {
    setAbnormal(unFixed || age2);
    setFlyable(!(unFixed || age2 || age1));
  }, [unFixed, age2]);

  useEffect(() => {
    if(abnormal){
      setLedColor(Colors.red);
    } else if (led.r !== -1) {
      var hexR = led.r.toString(16).padStart(2, '0');
      var hexG = led.g.toString(16).padStart(2, '0');
      var hexB = led.b.toString(16).padStart(2, '0');
      var c = '#' + hexR + hexG + hexB;
      setLedColor(c);
    } else {
      setLedColor(Colors.black);
    }
  }, [led, abnormal])

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
      {focused && (
        <mesh
          geometry={selector}
          rotation={[Math.PI, 0, 0]}
          position={[0, 1.1, 0]}
        >
          <meshPhongMaterial
            color={Colors.red}
            transparent={true}
          />
        </mesh>
      )}
      <mesh
        geometry={abnormal ? triangleBody : normalBody}
        rotation={abnormal ? [Math.PI, 0, 0] : [0, 0, 0]}
        position={[0, 0.25, 0]}
      >
        <meshPhongMaterial
          color={ledColor}
          transparent={true}
          opacity={0.8}
        />
      </mesh>
    </group>
  );
}

export default memo(SimpleDrone);