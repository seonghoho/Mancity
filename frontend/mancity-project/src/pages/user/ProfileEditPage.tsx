import { fetchProfileApi } from "@/apis/userApis";
import ProfileEditTemplate from "@/components/templates/ProfileEditTemplate";
import { useQuery } from "@tanstack/react-query";
import { useParams } from "react-router";

const ProfileEditPage = () => {
  const { user_id } = useParams<{ user_id: string }>();
  const { isLoading, data } = useQuery({
    queryKey: ["profile"],
    queryFn: () => fetchProfileApi(Number(user_id)),
  });

  return (
    !isLoading &&
    data && (
      <div>
        <ProfileEditTemplate profileData={data} />
      </div>
    )
  );
};
export default ProfileEditPage;
