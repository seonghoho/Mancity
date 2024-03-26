import ShadcnTabs from "@/components/atoms/shadcn_tabs/ShadcnTabs";
import FollowCard from "@/components/molecules/follow_card/FollowCard";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const FollowBody = () => {
  const [selectedTab, setSelectedTab] = useState(false);
  const tabSwitch = () => setSelectedTab(!selectedTab);

  const navigate = useNavigate();
  //   const handleNavigate = ({ path }: NavigateType) => {
  //     navigate(path);
  const file = "";
  const nickName = "김포이세은";
  const [isFollow, setIsFollow] = useState(false);

  return (
    <div>
      <div className="flex justify-center m-2">
        <ShadcnTabs
          Tab1="팔로잉"
          Tab2="팔로워"
          onChange={tabSwitch}
          defaultTab="tab1"
        />
      </div>
      <div className="p-2">
        {!selectedTab && (
          <div> 
            <div>
              <FollowCard
                file={file}
                nickName={nickName}
                isFollow={isFollow}
                setIsFollow={setIsFollow}
              />
            </div>
          </div>
        )}

        {selectedTab && (
          <div>
            <div className="flex">하이</div>
          </div>
        )}
      </div>
    </div>
  );
};

export default FollowBody;
