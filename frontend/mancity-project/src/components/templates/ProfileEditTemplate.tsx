import Header from "@/components/organisms/header/Header";
import ProfileEditBody from "@/components/organisms/profile_edit_body/ProfileEditBody";

const ProfileEditTemplate = ({ profileData }: ProfilePropsType) => {
  return (
    <div>
      <Header label="프로필 수정" backArrow={true} headerButton={false} />
      <ProfileEditBody profileData={profileData} />
    </div>
  );
};

export default ProfileEditTemplate;
