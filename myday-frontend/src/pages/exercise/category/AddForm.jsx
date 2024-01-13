import { Form, Input } from "antd";
import { useEffect } from "react";

function AddForm({ setForm }) {
  const [form] = Form.useForm();

  useEffect(() => {
    setForm(form);   // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <Form form={form} layout="vertical">
      <Form.Item
        name="name"
        label="Name:"
        rules={[
          {
            required: true,
            message: "Category name is required",
          },
          {
            pattern: /^[a-zA-Z0-9_]+$/,
            message: "Must be letters, numbers and underscore",
          },
        ]}
      >
        <Input placeholder="E.g. Cardio" />
      </Form.Item>
      <Form.Item name="avatar" label="Image URL">
        <Input.TextArea placeholder="Optional" />
      </Form.Item>
      <Form.Item name="desc" label="Description:">
        <Input.TextArea placeholder="Optional" />
      </Form.Item>
    </Form>
  );
}

export default AddForm;
