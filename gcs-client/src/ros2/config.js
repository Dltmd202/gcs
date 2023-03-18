import {Ros, Topic} from "roslib/src/core";

export const ros = new Ros();
ros.connect("ws://218.150.182.117:10081");

ros.on('error', function (error) {
  console.log('connection error');
  console.log(error);
});

// 정상 연결
ros.on('connection', function () {
  console.log('Connection made!');
});

// 연결 닫힘
ros.on('close', function () {
  console.log('Connection closed.');
});

const makeTopic = (i) => {
  return new Topic({
    ros : ros,
    name : `/vehicle${i}/out/VehicleGlobalPosition`,
    messageType : 'px4_msgs/VehicleGlobalPosition'
  })
};

export default makeTopic;