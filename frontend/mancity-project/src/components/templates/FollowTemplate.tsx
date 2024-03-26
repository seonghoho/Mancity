import FollowBody from "@/components/organisms/follow_body/FollowBody";
import Header from "@/components/organisms/header/Header";

const FollowTemplate = () => {
  return (
    <div>
      <Header label="친구목록" backArrow={true} headerButton={false} />
      <FollowBody />
    </div>
  );
};

export default FollowTemplate;
