import { publicRequest } from "@/hooks/requestMethods";

// 경기 상세 페이지 정보 조회
export const fetchMatchDetail = async (
  match_id: number
): Promise<matchDetailPropsDataType> => {
  return publicRequest
    .get(`social/game/detail/${match_id}`)
    .then((res) => res.data)
    .catch((error) => {
      console.log(error);
      throw new Error("경기 호출 에러");
    });
};

export const fetchMatchCreate = async (matchData: matchCreateType) => {
  return publicRequest
    .post(`social/game/create`, matchData)
    .then((res) => res.data)
    .catch((error) => {
      console.log(error);
      throw new Error("경기 호출 에러");
    });
};

// main page
export const MainPageApi = async (userId: number) => {
  return publicRequest
    .get(`user/main/${userId}`)
    .then((res) => res.data)
    .catch((error) => {
      console.log(error);
      throw new Error("main page api 호출 에러");
    });
};

// PUT 요청 API
// 분석할 영상 업로드
export const videoUploadApi = async (videoUploadData: FormData) => {
  return publicRequest
    .put(`social/game/upload`, videoUploadData)
    .then((res) => res.data)
    .catch((error) => {
      console.log(error);
      throw new Error("분석할 영상 업로드 api 에러");
    });
};

// 경기 분석 요청 API
export const calcRequestApi = async (match_id: number) => {
  return publicRequest
    .post(`social/game/calculate/${match_id}`)
    .then((res) => res.data)
    .catch((error) => {
      console.log(error);
      throw new Error("경기 분석 요청 에러");
    });
};
