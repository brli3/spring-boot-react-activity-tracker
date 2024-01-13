import { LockOutlined, UserOutlined } from "@ant-design/icons";
import { message, Row, Card, Button, Checkbox, Form, Input } from "antd";
import { Link, Navigate, useNavigate } from "react-router-dom";
import "./Login.css";
import { reqLogin } from "../../api";
import storageUtils from "../../utils/storageUtils";

function Login() {
  const user = storageUtils.getUser();
  const navigate = useNavigate();

  const onFinish = async (values) => {
    const { username, password } = values;
    const user = await reqLogin(username, password);
    console.log(user)
    if (user) {
      message.success("Login successful");
      storageUtils.saveUser(user);
      console.log("User logged in: " + user.username);
      navigate("/", { replace: true });
    } else {
      message.error(user);
    }
  };
  // Navigate to admin page if user is already logged in
  if (user && user.id) {
    return <Navigate to={"/"} />;
  }

  return (
    <div className="login">
      <Row justify="center">
        <section className="login-container">
          <Card title="User Login">
            <Form
              name="normal_login"
              className="login-form"
              initialValues={{
                remember: true,
              }}
              onFinish={onFinish}
            >
              <Form.Item
                name="username"
                rules={[
                  {
                    required: true,
                    message: "Please input your Username!",
                  },
                  {
                    min: 4,
                    message: "Minimum 4 characters",
                  },
                  {
                    max: 12,
                    message: "Maximum 12 characters",
                  },
                  {
                    pattern: /^[a-zA-Z0-9_]+$/,
                    message: "Must be letters, numbers and underscore",
                  },
                ]}
              >
                <Input
                  prefix={<UserOutlined className="site-form-item-icon" />}
                  placeholder="Username"
                />
              </Form.Item>
              <Form.Item
                name="password"
                rules={[
                  {
                    required: true,
                    message: "Please input your Password!",
                  },
                  {
                    min: 4,
                    message: "Minimum 4 characters",
                  },
                  {
                    max: 12,
                    message: "Maximum 12 characters",
                  },
                ]}
              >
                <Input
                  prefix={<LockOutlined className="site-form-item-icon" />}
                  type="password"
                  placeholder="Password"
                />
              </Form.Item>
              <Form.Item>
                <Form.Item name="remember" valuePropName="checked" noStyle>
                  <Checkbox>Remember me</Checkbox>
                </Form.Item>

                <Link className="login-form-forgot" to="#">
                  Forgot password
                </Link>
              </Form.Item>

              <Form.Item>
                <Button
                  type="primary"
                  htmlType="submit"
                  className="login-form-button"
                >
                  Log in
                </Button>
                Or <Link to="/register">register now!</Link>
              </Form.Item>
            </Form>
          </Card>
        </section>
      </Row>
    </div>
  );
}

export default Login;
