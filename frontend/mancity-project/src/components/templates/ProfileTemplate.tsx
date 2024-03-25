import GroupGlobalCard from "@/components/organisms/group_global_card/GroupGlobalCard";
import GroupHighlightCard from "@/components/organisms/group_highlight_card/GroupHighlightCard";
import Header from "@/components/organisms/header/Header";
import ProfileUserInfo from "@/components/organisms/profile_userInfo/ProfileUserInfo";
import ProfileUserStats from "@/components/organisms/profile_userStats/ProfileUserStats";

const ProfileTemplate = ({ profileData }: ProfilePropsType) => {
  return (
    <div>
      <Header
        label="프로필"
        backArrow={true}
        headerButton={true}
        buttonLabel="정보 수정"
        toWhere="/profile/edit/1"
      />
      <ProfileUserInfo profileData={profileData} />
      <ProfileUserStats />
      <GroupGlobalCard />
      <GroupHighlightCard />
    </div>
  );
};

export default ProfileTemplate;
