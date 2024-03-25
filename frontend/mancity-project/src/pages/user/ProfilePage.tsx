import { fetchProfileApi } from "@/apis/userApis";
import ProfileTemplate from "@/components/templates/ProfileTemplate";
import { useQuery } from "@tanstack/react-query";
import { useParams } from "react-router";

const ProfilePage = () => {
  const { user_id } = useParams<{ user_id: string }>();

  const { isLoading, data, error } = useQuery({
    queryKey: ["profile"],
    queryFn: () => fetchProfileApi(Number(user_id)),
  });

  return (
    !isLoading &&
    data && (
      <div>
        <ProfileTemplate profileData={data} />
      </div>
    )
  );
};

export default ProfilePage;
