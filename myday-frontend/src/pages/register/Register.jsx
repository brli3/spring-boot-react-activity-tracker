import { message, Row, Card, Button, Form, Input } from "antd";
import { Link, useNavigate } from "react-router-dom";
import { reqRegister } from "../../api";
import "./Register.css"

function Register() {
  const navigate = useNavigate();

  const onFinish = async (values) => {
    const { username, password, email } = values;
    const response = await reqRegister(username, password, email);
    if (response) {
      message.success("Register successful");
      console.log("User registered: " + username);
      navigate("/login", { replace: true });
    } 
  }
  const formItemLayout = {
    labelCol: {
      xs: {
        span: 26,
      },
      sm: {
        span: 10,
      },
    },
    wrapperCol: {
      xs: {
        span: 24,
      },
      sm: {
        span: 16,
      },
    },
  };
  const tailFormItemLayout = {
    wrapperCol: {
      xs: {
        span: 24,
        offset: 0,
      },
      sm: {
        span: 16,
        offset: 10,
      },
    },
  };

  return (
    <div className="register">
      <Row justify="center" align="middle">
        <section className="register-container">
          <Card title="User register">
            <Form
              {...formItemLayout}
              name="register"
              className="register-form"
              onFinish={onFinish}
            >
              <Form.Item
                name="username"
                label="Username"
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
                <Input placeholder="E.g. user_01"/>
              </Form.Item>
              <Form.Item
                name="email"
                label="E-mail"
                rules={[
                  {
                    type: "email",
                    message: "The input is not valid E-mail!",
                  },
                  {
                    required: true,
                    message: "Please input your E-mail!",
                  },
                ]}
              >
                <Input placeholder="abc@email.com"/>
              </Form.Item>

              <Form.Item
                name="password"
                label="Password"
                hasFeedback
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
                <Input.Password autoComplete="off"/>
              </Form.Item>

              <Form.Item
                name="confirm"
                label="Confirm Password"
                dependencies={["password"]}
                hasFeedback
                rules={[
                  {
                    required: true,
                    message: "Please confirm your password!",
                  },
                  ({ getFieldValue }) => ({
                    validator(_, value) {
                      if (!value || getFieldValue("password") === value) {
                        return Promise.resolve();
                      }

                      return Promise.reject(
                        new Error(
                          "Passwords entered do not match!"
                        )
                      );
                    },
                  }),
                ]}
              >
                <Input.Password autoComplete="off"/>
              </Form.Item>

              <Form.Item {...tailFormItemLayout}>
                <Button
                  type="primary"
                  htmlType="submit"
                  className="register-form-button"
                >
                  Register
                </Button>
              </Form.Item>
              <Form.Item {...tailFormItemLayout}>
                Already registered?
                <Button type="link" htmlType="submit">
                  <Link to="/login">Login</Link>
                </Button>
              </Form.Item>
            </Form>
          </Card>
        </section>
      </Row>
    </div>
  );
}

export default Register;
