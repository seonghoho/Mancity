
const RoundButton = (props: TailwindPropsType) => {
  const {
    textColor,
    bgColor,
    borderColor,
    hoverTextColor,
    hoverBgColor,
    hoverBorderColor,
    label,
  } = props;
  return (
    <button
      type="button"
      className={` ${textColor} ${bgColor} ${borderColor} ${hoverTextColor} ${hoverBgColor} ${hoverBorderColor} 
      w-14 h-14 font-medium text-sm rounded-full border-2 hover:border-2`}
    >
      {label}
    </button>
  );
};

export default RoundButton;
