import { usePlane } from "@react-three/cannon";

export const ThreeGround = () => {
  const [ref] = usePlane(() => ({
    rotation: [-Math.PI / 2, 0, 0],
    position: [0, 0, 0],
  }));
  return (
    <mesh ref={ref}>
      <planeGeometry attach="geometry" args={[1000, 1000]} />
      <meshStandardMaterial color={"green"}/>
    </mesh>
  );
};

export default ThreeGround;