import { checkEmailApi, checkNicknameApi } from "@/apis/userApis";
import GlobalInput from "@/components/atoms/global_input/GlobalInput";
import SubButton from "@/components/atoms/sub_button/SubButton";
import Typography from "@/components/atoms/typography/Typography";
import { useMutation } from "@tanstack/react-query";

const InputGroup = (props: InputGroupPropsType) => {
  const {
    type,
    typographyLabel,
    placeholder,
    checking,
    checkingLabel = "중복 확인",
    setIsCheck,
    textValue,
    setTextValue,
  } = props;

  // 이메일 유효성 로직
  const { mutate: emailMutate } = useMutation({
    mutationFn: checkEmailApi,
    onSuccess(result: boolean) {
      if (setIsCheck) {
        if (!result) {
          console.log("사용 가능한 이메일입니다.");
          setIsCheck(false);
        } else {
          console.log("사용할 수 없는 이메일입니다.");
        }
      }
    },
    onError(error) {
      console.log(error);
    },
  });

  // 닉네임 유효성 로직
  const { mutate: nicknameMutate } = useMutation({
    mutationFn: checkNicknameApi,
    onSuccess(result: boolean) {
      if (setIsCheck) {
        if (!result) {
          console.log("사용 가능한 닉네임입니다.");
          setIsCheck(false);
        } else {
          console.log("사용할 수 없는 닉네임입니다.");
        }
      }
    },
    onError(error) {
      console.log(error);
    },
  });

  const handleCheckingClick = () => {
    if (textValue) {
      if (typographyLabel === "이메일") {
        emailMutate(textValue);
      } else if (typographyLabel === "닉네임") {
        nicknameMutate(textValue);
      }
    }
  };

  return (
    <div className="flex m-4">
      <div className="w-full">
        <Typography
          textSize="text-sm"
          fontWeight="font-medium"
          textColor="text-sofcity"
          label={typographyLabel}
        />
        <GlobalInput
          type={type}
          width="w-full"
          placeholder={placeholder}
          textValue={textValue}
          setTextValue={setTextValue}
        />
      </div>
      {/* checkingLabel 존재하면 단순 입력, 존재하지 않으면 중복 확인 */}
      {checking && (
        <div
          className="mt-5"
          onClick={
            checkingLabel === "중복 확인" ? handleCheckingClick : undefined
          }
        >
          <SubButton label={checkingLabel} />
        </div>
      )}
    </div>
  );
};

export default InputGroup;
