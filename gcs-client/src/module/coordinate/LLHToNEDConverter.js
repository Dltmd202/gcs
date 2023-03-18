const LLHToNEDConverter = (
    Coordinate,
    referenceCoordinate
) => {
    const WGS84_A = 6378137.0; // earth's semi-major axis (m)
    const WGS84_F = 1.0 / 298.257223563; // earth's flattening

    // Convert latitude, longitude and altitude to radians
    const lat1 = Coordinate.lat * (Math.PI / 180);
    const lon1 = Coordinate.lng * (Math.PI / 180);
    const h1 = Coordinate.alt;

    // Convert reference latitude, longitude and altitude to radians
    const lat0 = referenceCoordinate.lat * (Math.PI / 180);
    const lon0 = referenceCoordinate.lng * (Math.PI / 180);
    const h0 = referenceCoordinate.alt;

    // Calculate earth's semi-minor axis (m)
    const WGS84_B = WGS84_A * (1 - WGS84_F);

    // Calculate eccentricity squared
    const e2 = (Math.pow(WGS84_A, 2) - Math.pow(WGS84_B, 2)) / Math.pow(WGS84_A, 2);

    // Calculate radius of curvature in the prime vertical
    const N = WGS84_A / Math.sqrt(1 - e2 * Math.pow(Math.sin(lat0), 2));

    // Calculate local radius of curvature in the prime vertical
    const M = (WGS84_A * (1 - e2)) / Math.pow(1 - e2 * Math.pow(Math.sin(lat0), 2), 1.5);

    // Calculate ECEF coordinates of reference point
    const x0 = (N + h0) * Math.cos(lat0) * Math.cos(lon0);
    const y0 = (N + h0) * Math.cos(lat0) * Math.sin(lon0);
    const z0 = ((1 - e2) * N + h0) * Math.sin(lat0);

    // Calculate ECEF coordinates of current point
    const x1 = (N + h1) * Math.cos(lat1) * Math.cos(lon1);
    const y1 = (N + h1) * Math.cos(lat1) * Math.sin(lon1);
    const z1 = ((1 - e2) * N + h1) * Math.sin(lat1);

    // Calculate delta ECEF coordinates
    const dx = x1 - x0;
    const dy = y1 - y0;
    const dz = z1 - z0;

    // Calculate rotation matrix elements
    const phi = lat0;
    const lambda = lon0;
    const sinPhi = Math.sin(phi);
    const cosPhi = Math.cos(phi);
    const sinLambda = Math.sin(lambda);
    const cosLambda = Math.cos(lambda);
    const R11 = -sinLambda;
    const R12 = cosLambda;
    const R13 = 0;
    const R21 = -sinPhi * cosLambda;
    const R22 = -sinPhi * sinLambda;
    const R23 = cosPhi;
    const R31 = cosPhi * cosLambda;
    const R32 = cosPhi * sinLambda;
    const R33 = sinPhi;

    // Calculate NED coordinates
    const north = R11 * dx + R21 * dy + R31 * dz;
    const east = R12 * dx + R22 * dy + R32 * dz;
    const down = R13 * dx + R23 * dy

    console.log([north, east, down])
    return [north * 10, east * 10, down * 10];
}

export default LLHToNEDConverter;
