import { signupApi } from "@/apis/userApis";
import GlobalButton from "@/components/atoms/global_button/GlobalButton";
import Typography from "@/components/atoms/typography/Typography";
import Dropdown from "@/components/molecules/dropdown/Dropdown";
import InputGroup from "@/components/molecules/input_group/InputGroup";
import { useMutation } from "@tanstack/react-query";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const SignUpBody = () => {
  const navigate = useNavigate();

  const GenderInfo = [
    { value: 1, label: "남자" },
    { value: 2, label: "여자" },
  ];
  const MainFootInfo = [
    { value: 1, label: "왼발" },
    { value: 2, label: "오른발" },
  ];

  const [genderPosition, setGenderPosition] = useState("click");
  const [genderValue, setGenderValue] = useState(0);
  const [mainFootPosition, setMainFootPosition] = useState("click");
  const [mainFootValue, setMainFootValue] = useState(0);
  const [emailValue, setEmailValue] = useState("");
  const [passwordValue, setPasswordValue] = useState("");
  const [secondPasswordValue, setSecondPasswordValue] = useState("");
  const [nickNameValue, setNickNameValue] = useState("");
  const [birthValue, setBirthValue] = useState("");
  const [heightValue, setHeightValue] = useState("");
  const [weightValue, setWeightValue] = useState("");
  const [isEmailCheck, setIsEmailCheck] = useState(true);
  const [isNicknameCheck, setIsNicknameCheck] = useState(true);

  const [signupData, setSignupData] = useState({
    email: "",
    password: "",
    nickName: "",
    birth: 0,
    gender: 0,
    mainFoot: 0,
    height: 0,
    weight: 0,
  });

  const { mutate } = useMutation({
    mutationFn: signupApi,
    onSuccess(result: string) {
      console.log(result);
    },
    onError(error) {
      console.log(error);
    },
  });

  //유효성 검사
  // 이메일 형식 검사
  const validateEmail = (email: string) => {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
  };

  // 비밀번호 형식 검사 (문자, 숫자, 특수문자 포함 8자 이상)
  const validatePassword = (password: string) => {
    const re = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*]).{8,}$/;
    return re.test(password);
  };

  // 비밀번호 확인 검사
  const validateSecondPassword = (password: string, secondPassword: string) => {
    return password === secondPassword;
  };

  // 닉네임, 생년월일, 성별, 주발, 키, 몸무게 등 나머지 필드들에 대한 간단한 빈 값 검사
  // 예를 들어, 닉네임이 비어있지 않은지 확인
  const isNotEmpty = (value: string) => {
    return value.trim() !== "";
  };

  // 유효성 검사 상태 추가
  const [isFormValid, setIsFormValid] = useState(false);

  useEffect(() => {
    // 모든 입력값의 유효성 검사
    const isValid =
      validateEmail(emailValue) &&
      validatePassword(passwordValue) &&
      validateSecondPassword(passwordValue, secondPasswordValue) &&
      isNotEmpty(nickNameValue) &&
      isNotEmpty(birthValue) &&
      genderValue !== 0 &&
      mainFootValue !== 0 &&
      isNotEmpty(heightValue) &&
      isNotEmpty(weightValue) &&
      isEmailCheck == false &&
      isNicknameCheck == false;

    setIsFormValid(isValid);
  }, [
    emailValue,
    passwordValue,
    secondPasswordValue,
    nickNameValue,
    birthValue,
    genderValue,
    mainFootValue,
    heightValue,
    weightValue,
    isEmailCheck,
    isNicknameCheck,
  ]);

  useEffect(() => {
    if (isFormValid) {
      setSignupData({
        email: emailValue,
        password: passwordValue,
        nickName: nickNameValue,
        birth: Number(birthValue),
        gender: genderValue,
        mainFoot: mainFootValue,
        height: Number(heightValue),
        weight: Number(weightValue),
      });
    }
  }, [isFormValid]);

  // 회원가입 제출
  const onSubmitSignup = () => {
    console.log(signupData);
    console.log("회원가입 정보 제출");
    if (isFormValid) {
      mutate(signupData);
    }
    // navigate("/");
  };

  return (
    <div>
      <div className="my-4">
        <InputGroup
          typographyLabel="이메일"
          placeholder="ssafy@email.com"
          checking={true}
          textValue={emailValue}
          setTextValue={setEmailValue}
          setIsCheck={setIsEmailCheck}
        />
        <div className="text-mancity mx-4 -my-3 ">
          {emailValue && !validateEmail(emailValue) && (
            <Typography
              textSize="text-sm"
              label="이메일 형식이 맞지 않습니다"
            />
          )}
        </div>
      </div>
      <div className="my-8">
        <InputGroup
          typographyLabel="비밀번호"
          placeholder="영문, 숫자, 특수문자 포함 8자리 이상"
          checking={false}
          textValue={passwordValue}
          setTextValue={setPasswordValue}
        />
        <div className="text-mancity mx-4 -my-3 ">
          {passwordValue && !validatePassword(passwordValue) && (
            <Typography
              textSize="text-sm"
              label="비밀번호 형식이 맞지 않습니다"
            />
          )}
        </div>
      </div>
      <div className="my-8">
        <InputGroup
          typographyLabel="비밀번호 확인"
          placeholder="영문, 숫자, 특수문자 포함 8자리 이상"
          checking={false}
          textValue={secondPasswordValue}
          setTextValue={setSecondPasswordValue}
        />
        <div className="text-mancity mx-4 -my-3 ">
          {secondPasswordValue &&
            !validateSecondPassword(passwordValue, secondPasswordValue) && (
              <Typography
                textSize="text-sm"
                label="비밀번호가 일치하지 않습니다"
              />
            )}
        </div>
      </div>
      <div className="my-6">
        <InputGroup
          typographyLabel="닉네임"
          checking={true}
          textValue={nickNameValue}
          setTextValue={setNickNameValue}
          setIsCheck={setIsNicknameCheck}
        />
      </div>
      <div className="mt-6">
        <InputGroup
          typographyLabel="생년월일"
          placeholder="ex) 990503"
          checking={false}
          textValue={birthValue}
          setTextValue={setBirthValue}
        />
      </div>
      <div className="flex flex-row ">
        <div className="w-2/4">
          <Dropdown
            typographyLabel="성별"
            items={GenderInfo}
            position={genderPosition}
            setPosition={setGenderPosition}
            setNumberValue={setGenderValue}
          />
        </div>
        <div className="w-2/4">
          <Dropdown
            typographyLabel="주발"
            items={MainFootInfo}
            position={mainFootPosition}
            setPosition={setMainFootPosition}
            setNumberValue={setMainFootValue}
          />
        </div>
      </div>
      <div className="mb-2">
        <div className="flex flex-row ">
          <div className="w-2/4">
            <InputGroup
              typographyLabel="키"
              placeholder="175"
              checking={false}
              textValue={heightValue}
              setTextValue={setHeightValue}
            />
          </div>
          <div className="w-2/4">
            <InputGroup
              typographyLabel="몸무게"
              placeholder="70"
              checking={false}
              textValue={weightValue}
              setTextValue={setWeightValue}
            />
          </div>
        </div>
      </div>
      <div onClick={onSubmitSignup}>
        <GlobalButton
          width="w-full"
          label="회원가입"
          isdisabled={isFormValid}
        />
      </div>
    </div>
  );
};

export default SignUpBody;
