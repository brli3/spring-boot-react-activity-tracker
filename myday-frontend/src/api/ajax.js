import axios from "axios";
import { message } from "antd";

export default function ajax(url, data = {}, method = "get", headers={}) {
  let promise;

  if (method === "get") {
    promise = axios({
      method,
      url,
      params: data,
      headers,
    });
  } else {
    promise = axios({
      method,
      url,
      data,
      headers,
    });
  }

  return new Promise((resolve, _) => {
    promise
      .then((response) => {
        resolve(response.data);
      })
      .catch((err) => {
        if (err.response) {
          message.error(err.response.data.message || err.response.data);
        } else {
          message.error(err.message || err);
        }
      });
  });
}
