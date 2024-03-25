// import axios from "axios";

// const API_URL = "http://127.0.0.1:xxxx";
// const API_URL_FLASK = "http://127.0.0.1:xxxx";

// GET 요청 API
// POST 요청 API
// PUT 요청 API
// PATCH 요청 API
// DELETE 요청 API

// 이메일 중복 확인
import { publicRequest } from "@/hooks/requestMethods";
export const checkEmailApi = async (emailValue: string | number) => {
  return publicRequest
    .post(`/user/check/email`, { email: emailValue })
    .then((res) => res.data)
    .catch((error) => {
      console.log(error);
      throw new Error("이메일 중복 api 에러");
    });
};

// 닉네임 중복 확인
export const checkNicknameApi = async (nicknameValue: string | number) => {
  return publicRequest
    .post(`/user/check/nickname`, { nickname: nicknameValue })
    .then((res) => res.data)
    .catch((error) => {
      console.log(error);
      throw new Error("닉네임 중복 api 에러");
    });
};

// 회원가입
export const signupApi = async (signupData: signupApiType) => {
  return publicRequest
    .post(`user/signup`, signupData)
    .then((res) => res.data)
    .catch((error) => {
      console.log(error);
      throw new Error("회원가입 api 에러");
    });
};
