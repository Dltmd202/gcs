import {BrowserRouter, Route, Routes} from "react-router-dom";
import LoginPage from "../pages/user/LoginPage";
import PublicRoute from "./PublicRoute";
import ConfigurationLoadPage from "../pages/load/ConfigurationLoadPage";
import PrivateRouter from "./PrivateRoute";
import UserPage from "../pages/user/UserPage";
import JoinPage from "../pages/user/JoinPage";
import ThreeDimensionGCSPage from "../pages/gcs/ThreeDimensionGCSPage";
import PlayGround from "../pages/gcs/PlayGround";
import ProtectRoute from "./ProtectRoute";
import NotFoundPage from "../pages/error/NotFound";

const Routers = () => {
  return(
    <BrowserRouter>
      <Routes>

        <Route element={<PublicRoute restricted={true}/> }>
          <Route path="/" element={<LoginPage />} />
          <Route path="/join" element={<JoinPage />} />
        </Route>

        <Route element={<PublicRoute restricted={false}/> }>
          <Route path="/play" element={<PlayGround />} />
          <Route path="/mypage" element={<UserPage />} />
        </Route>


        <Route element={<PrivateRouter/>}>
          <Route path="/gcs/three" element={<ThreeDimensionGCSPage/>} />
        </Route>

        <Route element={<ProtectRoute/>}>
          <Route path="/load" element={<ConfigurationLoadPage />} />
        </Route>

        <Route path="/*" element={<NotFoundPage/>}/>

      </Routes>
    </BrowserRouter>
  )
}

export default Routers;