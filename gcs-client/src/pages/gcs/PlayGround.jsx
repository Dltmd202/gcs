import CanvasContainer from "../../components/templates/Container/CanvasContainer";
import {useEffect, useRef, useState} from "react";
import SimpleDrone from "../../components/atoms/three/SimpleDrone";
import SimplePathDrone from "../../components/atoms/three/SimplePathDrone";
import SimpleDrone2 from "../../components/atoms/three/SimpleDrone2";
import SimplePathDrone2 from "../../components/atoms/three/SimplePathDrone2";

const PlayGround = () => {
  const cameraRef = useRef();
  const controlRef = useRef();
  const [models, setModels] = useState();


  useEffect(() => {
  }, [])

  return (
    <CanvasContainer cameraRef={cameraRef} controlRef={controlRef}>
      <SimpleDrone
        agentObj={{
          active: true,
          color: '#0000FF',
          complementaryColor: '#00FF00',
          angle: {
            roll: 0,
            pitch: 0,
            yaw: 0
          },
          ned:{x: 1.3, y: 4, z: -1}
        }}
        onClick={() => {}}
      />
      <SimplePathDrone
        agentObj={{
          active: true,
          color: '#0000FF',
          complementaryColor: '#00FF00',
          angle: {
            roll: 0,
            pitch: 0,
            yaw: 0
          },
          ned:{x: 2, y: 4, z: 1}
        }}
        onClick={() => {}}
      />

      <SimpleDrone2
        position={{x: 1.3, y: -4, z: -1}}
        agentObj={{
          active: true,
          color: '#0000FF',
          complementaryColor: '#00FF00',
          angle: {
            roll: 0,
            pitch: 0,
            yaw: 0
          },
          ned:{x: 0, y: 0, z: -1}
        }}
        onClick={() => {}}
      />

      <SimplePathDrone2
        position={{x: 1.3, y: -4, z: -1}}
        agentObj={{
          active: true,
          color: '#0000FF',
          complementaryColor: '#00FF00',
          angle: {
            roll: 0,
            pitch: 0,
            yaw: 0
          },
          ned:{x: 5, y: -4, z: -1}
        }}
        onClick={() => {}}
      />


    </CanvasContainer>
  )
}

export default PlayGround;