import ShadcnSwitch from "@/components/atoms/shadcn_switch/ShadcnSwitch";
import Typography from "@/components/atoms/typography/Typography";
import { useEffect } from "react";

const GlobalSwitch = (props: GlobalSwitchPropsType) => {
  const { label, isSwitchOn, setIsSwitchOn, switchMarginTop } = props;

  const toggleSwitch = () => setIsSwitchOn(!isSwitchOn);

  useEffect(() => {
    console.log(isSwitchOn);
  }, [isSwitchOn]);

  return (
    <div className="flex items-center justify-end mx-4">
      <div>
        <Typography
          label={label}
          textSize="text-sm"
          fontWeight="font-medium"
          textColor="text-sofcity"
        />
      </div>
      <div className={`${switchMarginTop}`}>
        <ShadcnSwitch checked={isSwitchOn} onCheckedChange={toggleSwitch} />
      </div>
    </div>
  );
};

export default GlobalSwitch;
