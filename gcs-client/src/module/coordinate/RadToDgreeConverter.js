const radianToDegree = (rad) => {
  return rad * (180 / Math.PI);
}

export const degreeToRadian = (degree) => {
  return degree * (Math.PI / 180);
}

export default radianToDegree;