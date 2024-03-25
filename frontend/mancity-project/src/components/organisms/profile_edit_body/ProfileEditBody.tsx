import ReverseButton from "@/components/atoms/reverse_button/ReverseButton";
import Dropdown from "@/components/molecules/dropdown/Dropdown";
import EditContentBox from "@/components/molecules/edit_content_box/EditContentBox";
import InputGroup from "@/components/molecules/input_group/InputGroup";
import GlobalSwitch from "@/components/molecules/global_switch/GlobalSwitch";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const ProfileEditBody = ({ profileData }: ProfilePropsType) => {
  const navigate = useNavigate();
  const MainFootInfo = [
    { value: 1, label: "왼발" },
    { value: 2, label: "오른발" },
  ];

  const [mainFootPosition, setMainFootPosition] = useState(profileData.foot);
  const [mainFootValue, setMainFootValue] = useState(profileData.foot);
  const [nickNameValue, setNickNameValue] = useState(profileData.nickName);
  const [heightValue, setHeightValue] = useState(profileData.height);
  const [weightValue, setWeightValue] = useState(profileData.weight);

  const [isSwitchOn, setIsSwitchOn] = useState(false);

  const isNotEmpty = (value: string) => {
    return value.trim() !== "";
  };

  const [isFormValid, setIsFormValid] = useState(false);

  useEffect(() => {
    // 모든 입력값의 유효성 검사
    const isValid =
      isNotEmpty(nickNameValue) &&
      mainFootValue !== 0 &&
      heightValue !== 0 &&
      weightValue != 0;

    setIsFormValid(isValid);
  }, [nickNameValue, mainFootValue, heightValue, weightValue]);

  const onEditProfile = () => {
    console.log("수정된 회원정보 제출");
    navigate("/");
  };

  return (
    <div className="">
      <div className=" flex m-3 justify-center  ">
        <EditContentBox width="w-36" height="h-36" rounded="rounded-full" />
      </div>
      <div className="my-6">
        <InputGroup
          typographyLabel="닉네임"
          checking={true}
          textValue={nickNameValue}
          setTextValue={setNickNameValue}
        />
      </div>
      <div className="mb-6">
        <div className="flex flex-row ">
          <div className="w-2/4">
            <InputGroup
              typographyLabel="키 (cm)"
              placeholder="175"
              checking={false}
              textValue={heightValue}
              setTextValue={setHeightValue}
            />
          </div>
          <div className="w-2/4">
            <InputGroup
              typographyLabel="몸무게 (kg)"
              placeholder="70"
              checking={false}
              textValue={weightValue}
              setTextValue={setWeightValue}
            />
          </div>
        </div>
        <div className="flex flex-row -mr-2">
          <div className="w-1/2">
            <Dropdown
              typographyLabel="주 발"
              items={MainFootInfo}
              position={mainFootPosition}
              setPosition={setMainFootPosition}
              setNumberValue={setMainFootValue}
            />
          </div>
          <div className="w-1/2 flex     -ml-2 -mt-4">
            {/* checked에는 해당 회원이 등록허용 해 뒀는지 값을 넣기 */}
            <GlobalSwitch
              label="용병등록"
              isSwitchOn={isSwitchOn}
              setIsSwitchOn={setIsSwitchOn}
              switchMarginTop="ml-8 mt-16 "
            />
          </div>
        </div>
        <div className=" flex justify-end">
          <div className="text-red-500 text-right mr-4 cursor-pointer">
            회원탈퇴
          </div>
        </div>
      </div>
      <div onClick={onEditProfile} className="flex justify-end m-4 ">
        <ReverseButton
          width="w-full"
          label="수정 완료"
          isdisabled={isFormValid}
        />
      </div>
    </div>
  );
};

export default ProfileEditBody;
