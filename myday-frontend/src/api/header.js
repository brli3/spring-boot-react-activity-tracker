import storageUtils from "../utils/storageUtils"

export const authHeader = () => {
  const user = storageUtils.getUser();
  if (user && user.accessToken) {
    return { Authorization: "Bearer " + user.accessToken};
  }
  return {};
}