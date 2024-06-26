import {
  BrowserRouter as Router,
  matchPath,
  useLocation,
} from "react-router-dom";
import AppRoutes from "./routes";
import tooSmallImage from "./assets/imgs/toosmall.png";
import Navbar from "./components/molecules/navbar/Navbar";
import { useEffect } from "react";

const App = () => {
  // 알림 권한 요청 함수
  const requestPermission = () => {
    Notification.requestPermission().then((permission) => {
      if (permission === "granted") {
        // 테스트 콘솔
        // console.log("알림 권한이 허용됨");
      } else {
        console.log("알림 권한 허용 안됨");
      }
    });
  };

  useEffect(() => {
    requestPermission();
  }, []);

  // 하단바 조건부 렌더링 설정
  const ConditionalBottomNav = () => {
    const location = useLocation();
    const currentPathname = location.pathname;

    // Navbar 사용하지 않는 페이지 예외 처리 코드
    if (
      location.pathname === "/entry" ||
      location.pathname === "/login" ||
      location.pathname === "/signUp" ||
      location.pathname === "/alert" ||
      location.pathname === "/map" ||
      location.pathname === "/match/register" ||
      location.pathname === "/community/register" ||
      location.pathname === "/community/edit" ||
      location.pathname === "/club/register" ||
      matchPath("/match/:match_id", currentPathname) ||
      // matchPath("/replay/:match_id", currentPathname) ||
      matchPath("/community/:community_id", currentPathname) ||
      matchPath("/club/:club_id", currentPathname) ||
      matchPath("/club/edit/:club_id", currentPathname) ||
      matchPath("/playerfix/:match_id", currentPathname) ||
      matchPath("/profile/edit/:user_id", currentPathname)
    ) {
      return null;
    }

    return (
      <>
        <Navbar />
        <div className="h-20"></div>
      </>
    );
  };

  return (
    <>
      {/* 화면 크기 너무 작으면 서비스 이용 제한 */}
      <div id="appWarning" className="TooSmall">
        <img src={tooSmallImage} alt="toosmall" />
        <p>맨시티를 즐기기엔 화면이 너무 작아요..</p>
      </div>
      <div className="AppSize">
        <Router>
          <AppRoutes />
          <ConditionalBottomNav />
        </Router>
      </div>
    </>
  );
};

export default App;
