import { loginApi } from "@/apis/userApis";
import GlobalButton from "@/components/atoms/global_button/GlobalButton";
import Typography from "@/components/atoms/typography/Typography";
import InputGroup from "@/components/molecules/input_group/InputGroup";
import { useMutation } from "@tanstack/react-query";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router";

const LoginBody = () => {
  const [emailValue, setEmailValue] = useState("");
  const [passwordValue, setPasswordValue] = useState("");
  const [isFormValid, setIsFormValid] = useState(false);
  const [LoginError, setLoginError] = useState("");
  const [loginData, setLoginData] = useState({
    email: "",
    password: "",
  });
  const navigate = useNavigate();

  const goSignup = () => {
    navigate("/signup");
  };
  const validateEmail = (email: string) => {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
  };

  const validatePassword = (password: string) => {
    const re = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*]).{8,}$/;
    return re.test(password);
  };

  useEffect(() => {
    const isValid =
      validateEmail(emailValue) && validatePassword(passwordValue);
    setIsFormValid(isValid);

    setLoginData({
      email: emailValue,
      password: passwordValue,
    });
  }, [emailValue, passwordValue]);

  const onSubmitLogin = () => {
    console.log("로그인 정보 제출");
    mutate(loginData);
    console.log(loginData);
    setLoginError("이메일 또는 비밀번호가 맞지 않습니다. 다시 확인해 주세요.");
  };

  const { mutate } = useMutation({
    mutationFn: loginApi,
    onSuccess() {
      console.log("로그인되었슴도");
    },
    onError() {
      console.log("여기서 왜 에러남?");
    },
  });

  return (
    <div>
      <div className="mt-6">
        <InputGroup
          typographyLabel="이메일"
          placeholder="ssafy@email.com"
          checking={false}
          textValue={emailValue}
          setTextValue={setEmailValue}
        />
      </div>
      <div className="">
        <InputGroup
          type="password"
          typographyLabel="비밀번호"
          placeholder="영문, 숫자, 특수문자 포함 8자리 이상"
          checking={false}
          textValue={passwordValue}
          setTextValue={setPasswordValue}
        />
      </div>
      <div className="text-mancity mx-4 -mt-2 mb-2 ">
        {LoginError !== "" && (
          <Typography textSize="text-sm" label={LoginError} />
        )}
      </div>
      <div className="mt-8" onClick={onSubmitLogin}>
        <GlobalButton label="로그인" width="w-full" isdisabled={isFormValid} />
      </div>

      <div
        className="flex text-sm justify-end mr-4 mt-4 hover:underline hover:cursor-pointer"
        onClick={goSignup}
      >
        <div className="mr-2">아직 회원이 아니신가요?</div>
        <div className="underline">
          <Typography label="회원가입" fontWeight="font-medium" textColor="" />
        </div>
      </div>
    </div>
  );
};

export default LoginBody;
