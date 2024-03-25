const GlobalInput = (props: TailwindPropsType) => {
  const { type = "text", placeholder, width, textValue, setTextValue } = props;

  const onChangeValue = (e: React.ChangeEvent<HTMLInputElement>) => {
    setTextValue?.(e.target.value);
  };
  return (
    <input
      type={type}
      value={textValue}
      onChange={onChangeValue}
      placeholder={placeholder}
      className={`${width} text-base px-1 py-1 border-b-[1px]  text-center text-sofcity border-sofcity
      focus:outline-none  placeholder-gray-400`}
    ></input>
  );
};

export default GlobalInput;
