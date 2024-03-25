import Header from "@/components/organisms/header/Header";
import LoginBody from "@/components/organisms/login_body/LoginBody";

const LoginTemplate = () => {
  return (
    <div>
      <Header label="로그인" backArrow={true} headerButton={false} />
      <LoginBody />
    </div>
  );
};

export default LoginTemplate;
