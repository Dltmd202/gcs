import {BrowserRouter, Route, Routes} from "react-router-dom";
import LoginPage from "../pages/user/LoginPage";
import PublicRoute from "./PublicRoute";
import ConfigurationLoadPage from "../pages/load/ConfigurationLoadPage";
import PrivateRouter from "./PrivateRoute";
import UserPage from "../pages/user/UserPage";
import JoinPage from "../pages/user/JoinPage";
import ThreeDimensionGCSPage from "../pages/gcs/ThreeDimensionGCSPage";
import ThreeDimensionGCSPage2 from "../pages/gcs/ThreeDimensionGCSPage2";
import ProtectRoute from "./ProtectRoute";
import NotFoundPage from "../pages/error/NotFound";
import WhiteGCSPage from "../pages/gcs/WhiteGCSPage";

const Routers = () => {
  return(
    <BrowserRouter>
      <Routes>

        <Route element={<PublicRoute restricted={true}/> }>
          <Route path="/" element={<LoginPage />} />
          <Route path="/join" element={<JoinPage />} />
        </Route>

        <Route element={<PublicRoute restricted={false}/> }>
          <Route path="/mypage" element={<UserPage />} />
        </Route>


        <Route element={<PrivateRouter/>}>
          <Route path="/gcs/three" element={<ThreeDimensionGCSPage/>} />
          <Route path="/gcs/three2" element={<ThreeDimensionGCSPage2/>} />
          <Route path="/gcs/white" element={<WhiteGCSPage/>} />
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