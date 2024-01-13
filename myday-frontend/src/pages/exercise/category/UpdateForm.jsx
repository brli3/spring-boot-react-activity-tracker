import { Form, Input } from "antd";
import { useEffect } from "react";

function UpdateForm({ categoryName, categoryDesc, categoryAvatar, setForm }) {
  const [form] = Form.useForm();

  useEffect(() => {
    setForm(form);  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  // Update fields when editing another category
  useEffect(() => {
    form.resetFields();  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [categoryName]);

  return (
    <Form layout="vertical" form={form}>
      <Form.Item
        name="name"
        label="Name:"
        initialValue={categoryName}
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
        <Input placeholder="" />
      </Form.Item>
      <Form.Item name="avatar" label="Image URL" initialValue={categoryAvatar}>
        <Input.TextArea placeholder="Optional" />
      </Form.Item>
      <Form.Item name="desc" label="Description:" initialValue={categoryDesc}>
        <Input.TextArea placeholder="Optional" />
      </Form.Item>
    </Form>
  );
}

export default UpdateForm;
