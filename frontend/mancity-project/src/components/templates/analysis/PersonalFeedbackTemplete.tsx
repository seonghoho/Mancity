import Header from "@/components/organisms/header/Header";
import MyTypography from "@/components/atoms/my_typography/MyTypography";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { fetchPersonalFeedbacksApi } from "@/apis/feedbackApis";
import useUserStore from "@/stores/userStore";

const presonalFeedbacks = [
  {
    id: 1,
    kind: "distanceCovered",
    feedback1:
      "높은 이동거리는 활동량이 높음을 나타냅니다. 이는 긍정적인 측면에서 팀의 열정과 에너지를 반영하지만, 에너지 관리와 효율적인 위치 선정의 필요성을 강조합니다. ",
    feedback2:
      "낮은 이동거리로 보아 경기 중 충분히 활동하지 못하고 있을 가능성이 있습니다. 위치 선정의 개선이나 경기 중 활동량을 높이기 위한 전략이 필요할 수 있습니다. ",
  },
  {
    id: 2,
    kind: "speed",
    feedback1:
      "최고속도는 선수의 체력과 스프린트 능력을 보여줍니다. 높은 최고속도는 역습과 빠른 공격 전환에서 유리하며, 팀에 역동성을 더해줍니다. 이를 유지하기 위해 지속적인 체력 관리와 폭발력 향상 훈련이 필요합니다. ",
    feedback2:
      "최고속도의 향상은 선수의 공격적인 기여도를 높이고, 역습의 효과를 증대시킬 수 있습니다. 체력과 스프린트 능력을 개선하기 위한 맞춤형 훈련 프로그램을 개발하고, 지속적인 노력이 필요합니다. ",
  },
  {
    id: 3,
    kind: "goal",
    feedback1:
      "높은 득점수는 우수한 공격 능력과 결정적인 순간에 골을 성공시킬 수 있는 능력을 나타냅니다. 이는 공격진의 효율성과 정확도가 높음을 의미합니다. 득점 기회를 최대한 활용하기 위해 계속해서 공격 전략을 다듬고, 개인의 기술을 향상시키는 것이 중요합니다. ",
    feedback2:
      "낮은 득점수는 공격진의 결정적인 순간의 효율성이나 정확도가 부족할 수 있음을 나타냅니다. 득점 기회를 놓치지 않기 위해서는 공격 전략의 개선과 개인 기술의 향상에 집중할 필요가 있습니다. ",
  },
  {
    id: 4,
    kind: "assist",
    feedback1:
      "높은 어시스트수는 팀워크와 상호 작용의 우수성을 나타내며, 팀 내에서 효과적인 커뮤니케이션과 이해도가 높음을 의미합니다. 선수들 사이의 좋은 화합과 전략적 이해를 바탕으로 계속해서 공격 기회를 만들어내는 것이 중요합니다. ",
    feedback2:
      "낮은 어시스트수는 팀 내 커뮤니케이션이나 전략적 이해도가 향상될 필요가 있음을 나타냅니다. 팀워크와 상호 작용을 강화하고, 공격 기회를 만들기 위한 전략적인 플레이에 더 집중할 필요가 있습니다. ",
  },
  {
    id: 5,
    kind: "shot",
    feedback1:
      "높은 슈팅수는 공격적인 경기 운영과 적극적인 골 시도를 나타냅니다. 골문을 향한 다양한 시도는 득점 기회를 높이며, 상대 팀에게 지속적인 압박을 가할 수 있습니다. 공격적인 자세를 유지하면서도 슈팅의 질을 높이는 방향으로 노력해야 합니다. ",
    feedback2:
      "낮은 슈팅수는 공격 기회의 활용도가 낮거나 공격 전략이 소극적일 수 있음을 나타냅니다. 공격 기회를 더 많이 창출하고, 골문을 향한 시도를 늘리기 위해 공격 전략과 선수들의 자신감을 키울 필요가 있습니다. ",
  },
  {
    id: 6,
    kind: "shotOnTarget",
    feedback1:
      "슈팅성공율은 팀의 공격 효율성을 나타내는 중요한 지표입니다. 높은 슈팅성공율은 정확한 슈팅 능력과 우수한 선택을 반영합니다. 이를 기반으로 더 많은 득점 기회를 창출하고, 상대방에 대한 압박을 지속적으로 유지할 수 있습니다. ",
    feedback2:
      "슈팅성공율을 개선하기 위해서는 슈팅 기술의 정확도와 선택의 질을 높이는 것이 중요합니다. 더 유리한 위치에서의 슈팅 연습을 통해 골문을 향한 시도의 효율을 증가시키고, 실전 같은 연습을 통해 의사결정 능력을 향상시켜야 합니다. ",
  },
  {
    id: 7,
    kind: "pass",
    feedback1:
      "높은 패스 수는 팀의 소유권 유지 능력을 나타내며, 패스의 질과 팀워크의 중요성을 강조합니다. 패스의 정확도와 전략적인 패스 선택이 중요합니다. ",
    feedback2:
      "낮은 패스 수는 공격 활로가 원활하게 연결되지 않는다는 것을 나타닙니다. 패스의 정확도와 전략적인 패스 선택이 중요합니다. ",
  },
  {
    id: 8,
    kind: "turnOverInOffense",
    feedback1:
      "턴오버의 감소는 팀의 소유권 관리 능력이 향상되고 있음을 의미합니다. 안정적인 패스와 전략적인 공격 운영을 통해 상대방에게 기회를 줄이고, 게임의 흐름을 자신들의 편으로 유도할 수 있습니다. ",
    feedback2:
      "턴오버를 줄이기 위해, 패스와 볼 핸들링의 정확도를 개선할 필요가 있습니다. 팀원들과의 커뮤니케이션 강화와 함께, 고압 상황에서의 의사결정 능력 향상이 필요합니다. ",
  },
  {
    id: 9,
    kind: "turnOverInDefense",
    feedback1:
      "높은 수비성공율은 팀의 수비 능력이 효과적임을 의미합니다. 수비 조직력을 유지하고, 개인의 수비 기술을 계속 개선하는 것이 중요합니다. ",
    feedback2:
      "낮은 수비성공율은 개선이 필요한 수비 전략이나 기술적인 문제를 지적합니다. 위치 선정, 대인 방어, 공간 커버 등 수비 훈련에 더 많은 시간을 할애하고, 경기 분석을 통해 반복되는 수비 실수를 파악해야 합니다. ",
  },
];

const PersonalFeedbackTemplete = () => {
  const { match_id } = useParams<{ match_id: string }>();
  const userId = useUserStore((state) => state.id);
  const [feedbackResult, setFeedbackResult] = useState<PersonalFeedbackResult>({
    distanceCovered: 2,
    speed: 2,
    goal: 2,
    assist: 2,
    shot: 2,
    shotOnTarget: 1,
    pass: 1,
    turnOverInOffense: 1,
    turnOverInDefense: 1,
  });

  useEffect(() => {
    if (match_id) {
      fetchPersonalFeedbacksApi(Number(match_id), userId).then((feedback) => {
        setFeedbackResult(feedback);
        console.log(feedbackResult);
      });
    }
  }, [match_id]);

  return (
    <div>
      <Header label="개인 기록" backArrow={true} headerButton={false} />

      <div id="glassui" className="py-1 mx-4 my-4">
        <div className="mx-4 my-2">
          <MyTypography
            label="개인 기록(선수#1 HOME)"
            textColor="black"
            textSize="text-2xl"
            fontWeight="font-medium"
          />
        </div>
        <hr className="mb-2 border border-sofcity" />

        {/* 개인 통계 */}
        {/* 이동 거리 */}
        <div className="flex">
          <div className="w-24 mx-1 my-2 text-end">
            <MyTypography
              fontWeight="font-medium"
              label="이동 거리:"
              textColor="text-darkcity"
              textSize="text-xl"
            />
          </div>
          <div className="mx-1 my-2">
            <MyTypography
              fontWeight="font-medium"
              label="285km"
              textColor="text-darkcity"
              textSize="text-xl"
            />
          </div>
        </div>

        {/* 최고속도 */}
        <div className="flex">
          <div className="w-24 mx-1 my-2 text-end">
            <MyTypography
              fontWeight="font-medium"
              label="최고 속도:"
              textColor="text-darkcity"
              textSize="text-xl"
            />
          </div>
          <div className="mx-1 my-2">
            <MyTypography
              fontWeight="font-medium"
              label="24km/h"
              textColor="text-darkcity"
              textSize="text-xl"
            />
          </div>
        </div>

        {/* 득점 */}
        <div className="flex">
          <div className="w-24 mx-1 my-2 text-end">
            <MyTypography
              fontWeight="font-medium"
              label="득점:"
              textColor="text-darkcity"
              textSize="text-xl"
            />
          </div>
          <div className="mx-1 my-2">
            <MyTypography
              fontWeight="font-medium"
              label="11"
              textColor="text-darkcity"
              textSize="text-xl"
            />
          </div>
        </div>

        {/* 어시스트 */}
        <div className="flex">
          <div className="w-24 mx-1 my-2 text-end">
            <MyTypography
              fontWeight="font-medium"
              label="도움:"
              textColor="text-darkcity"
              textSize="text-xl"
            />
          </div>
          <div className="mx-1 my-2">
            <MyTypography
              fontWeight="font-medium"
              label="2"
              textColor="text-darkcity"
              textSize="text-xl"
            />
          </div>
        </div>

        {/* 총슈팅 */}
        <div className="flex">
          <div className="w-24 mx-1 my-2 text-end">
            <MyTypography
              fontWeight="font-medium"
              label="총 슈팅:"
              textColor="text-darkcity"
              textSize="text-xl"
            />
          </div>
          <div className="mx-1 my-2">
            <MyTypography
              fontWeight="font-medium"
              label="21"
              textColor="text-darkcity"
              textSize="text-xl"
            />
          </div>
        </div>

        {/* 유효 슈팅 */}
        <div className="flex">
          <div className="w-24 mx-1 my-2 text-end">
            <MyTypography
              fontWeight="font-medium"
              label="유효 슈팅:"
              textColor="text-darkcity"
              textSize="text-xl"
            />
          </div>
          <div className="mx-1 my-2">
            <MyTypography
              fontWeight="font-medium"
              label="13"
              textColor="text-darkcity"
              textSize="text-xl"
            />
          </div>
        </div>

        {/* 총 패스 */}
        <div className="flex">
          <div className="w-24 mx-1 my-2 text-end">
            <MyTypography
              fontWeight="font-medium"
              label="총 패스:"
              textColor="text-darkcity"
              textSize="text-xl"
            />
          </div>
          <div className="mx-1 my-2">
            <MyTypography
              fontWeight="font-medium"
              label="34"
              textColor="text-darkcity"
              textSize="text-xl"
            />
          </div>
        </div>

        {/* 공격 턴오버 */}
        <div className="flex">
          <div className="w-24 mx-1 my-2 text-end">
            <MyTypography
              fontWeight="font-medium"
              label="공격 실패:"
              textColor="text-darkcity"
              textSize="text-xl"
            />
          </div>
          <div className="mx-1 my-2">
            <MyTypography
              fontWeight="font-medium"
              label="8"
              textColor="text-darkcity"
              textSize="text-xl"
            />
          </div>
        </div>

        {/* 수비 성공 */}
        <div className="flex">
          <div className="w-24 mx-1 my-2 text-end">
            <MyTypography
              fontWeight="font-medium"
              label="수비 성공:"
              textColor="text-darkcity"
              textSize="text-xl"
            />
          </div>
          <div className="mx-1 my-2">
            <MyTypography
              fontWeight="font-medium"
              label="12"
              textColor="text-darkcity"
              textSize="text-xl"
            />
          </div>
        </div>
      </div>

      {/* 개인 피드백 */}
      <div id="glassui" className="py-1 mx-4 my-4">
        <div className="mx-4 my-2">
          <MyTypography
            label="개인 피드백"
            textColor="black"
            textSize="text-2xl"
            fontWeight="font-medium"
          />
        </div>
        <hr className="mb-2 border border-sofcity" />
        <div className="mx-1 my-2">
          <MyTypography
            fontWeight="font-medium"
            label="내용:"
            textColor="text-darkcity"
            textSize="text-xl"
          />
        </div>
      </div>
    </div>
  );
};

export default PersonalFeedbackTemplete;
