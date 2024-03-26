const SubmitButton = (props: TailwindPropsType) => {
  const { label, reverse, hover } = props;
  return (
    <button
      type="button"
      className={` 
      w-16 font-medium text-[0.7rem] h-7 rounded-3xl border-[1.5px] border-sofcity
      ${
        hover
          ? // 호버 true일 뗴
            reverse
            ? // reverse true일 때
              "bg-sofcity text-white hover:bg-white hover:text-sofcity"
            : // reverse false일 때
              "bg-white text-sofcity hover:bg-sofcity hover:text-white"
          : // 호버 false일 때
            reverse
            ? "bg-sofcity text-white "
            : "bg-white text-sofcity "
      }
    `}
    >
      {label}
    </button>
  );
};

export default SubmitButton;
