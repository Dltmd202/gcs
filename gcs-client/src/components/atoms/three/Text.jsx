import {useRef} from "react";
import { TextGeometry } from 'three/examples/jsm/geometries/TextGeometry.js';
import {useLoader} from "@react-three/fiber";
import {FontLoader} from "three/examples/jsm/loaders/FontLoader";
import regFont from "../../assets/fonts/optimer_regular.typeface.json"


const TextMesh = ({
                    text,
                    color,
                    size,
                    ...props
}) => {
  const mesh = useRef();
  const font = new FontLoader().parse(regFont);

  const geometry = new TextGeometry(text, {
    font: font,
    size: size,
    height: 0.2,
    curveSegments: 0.5,
    bevelEnabled: false,
  });

  return(
    <mesh {...props} geometry={geometry} ref={mesh}>
      <meshBasicMaterial color={color} attach="material" />
    </mesh>
  )
}

export default TextMesh;