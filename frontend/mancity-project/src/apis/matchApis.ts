import { publicRequest } from "@/hooks/requestMethods";

export const fetchMatchDetail = async () => {
  return publicRequest
    .get(`social/game/1`)
    .then((res) => res.data)
    .catch((error) => {
      console.log(error);
      throw new Error("경기 호출 에러");
    });
};
