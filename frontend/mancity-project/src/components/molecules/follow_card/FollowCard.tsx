import ContentBox from "@/components/atoms/content_box/ContentBox";
import SubmitButton from "@/components/atoms/submit_button/SubmitButton";
import Typography from "@/components/atoms/typography/Typography";

const FollowCard = (props: FollowCardPropsType) => {
  const { file, nickName, isFollow, setIsFollow } = props;

  const onClickFollow = () => {
    setIsFollow(!isFollow);
  };
  return (
    <div>
      <div className="flex items-center justify-between mx-3">
        <div className="flex items-center justify-between">
          <div className="mr-2">
            <ContentBox
              file={file}
              height="h-10"
              rounded="rounded-full"
              width="w-10"
            />
          </div>
          <Typography
            fontWeight="font-medium"
            label={nickName}
            textColor="text-black"
            textSize="text-lg"
          />
        </div>
        <div onClick={onClickFollow}>
          {isFollow ? (
            <SubmitButton label="팔로우" hover={false} />
          ) : (
            <SubmitButton label="언팔로우" reverse={true} hover={false} />
          )}
        </div>
      </div>
      <div className="flex justify-center">
        <hr className="w-11/12 px-2 my-2" />
      </div>
    </div>
  );
};

export default FollowCard;
