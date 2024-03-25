import { fetchMatchDetail } from "@/apis/matchApis";
import ContentBox from "@/components/atoms/content_box/ContentBox";
import Typography from "@/components/atoms/typography/Typography";
import TypographyLine from "@/components/atoms/typography_line/TypographyLine";
import MemberList from "@/components/molecules/member_list/MemberList";
import MiniMap from "@/components/molecules/mini_map/MiniMap";
import { useQuery } from "@tanstack/react-query";

const MatchDetailBody = () => {
  const address = "광주시 광산구 장덕동 82-3";

  const onClickCopy = async (text: string) => {
    try {
      await navigator.clipboard.writeText(text);
      alert("클립보드에 링크가 복사되었어요.");
    } catch (err) {
      console.log(err);
    }
  };

  // MatchDatail get 호출
  const { data } = useQuery({
    queryKey: ["matchDetail"],
    queryFn: fetchMatchDetail,
  });

  console.log(data);

  return (
    <div>
      {/* 지도 */}
      <MiniMap
        lat={35.2037466}
        lng={126.8143846}
        address="광주시 광산구 장덕동 82-3"
        tel="062-951-9876"
        onClick={() => onClickCopy(address)}
      />
      {/* 경기 분석하기 컴포넌트 */}
      <div className="relative h-32 m-2 duration-300 ease-in-out cursor-pointer hover:opacity-60">
        <div className="absolute inset-0 transition-opacity ">
          <ContentBox
            // bgimg="bg-[url('@/assets/imgs/go_to_analysis.jpg')]"
            file="/src/assets/imgs/go_to_analysis.jpg"
            width="w-full"
            height="h-full"
            rounded="rounded-3xl"
          />
        </div>
        <div className="relative z-10 flex items-center justify-center h-full">
          <Typography
            label="경기 분석하기"
            textColor="text-white"
            fontWeight="font-semibold"
            textSize="text-2xl"
          />
        </div>
      </div>
      {/* 멤버 라인업 */}
      <div className="">
        <MemberList label="멤버 라인업" />
      </div>
      {/* 경기 다시보기 */}
      <div>
        <TypographyLine lineWidth="w-40" label="경기 분석하기" />
        <div className="m-2 rounded-xl">
          <ContentBox width="w-full" height="h-40" rounded="rounded-xl" />
        </div>
      </div>
    </div>
  );
};

export default MatchDetailBody;
