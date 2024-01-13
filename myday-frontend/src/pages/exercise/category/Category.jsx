import { Card, Button, Modal, message, Image, Tooltip, } from "antd";
import {
  PlusOutlined,
  ExclamationCircleOutlined,
  EditOutlined,
  DeleteOutlined,
  EllipsisOutlined,
} from "@ant-design/icons";
import {
  reqAddExerciseCategory,
  reqDeleteExerciseCategory,
  reqVisibleExerciseCategories,
  reqUpdateExerciseCategory,
} from "../../../api";
import { useEffect } from "react";
import { useState } from "react";
import AddForm from "./AddForm";
import UpdateForm from "./UpdateForm";
import './Category.css';

const { Meta } = Card;

const title = "Exercise Category";

function Category() {
  // Category list
  const [categories, setCategories] = useState([]);
  // Category to edit
  const [category, setCategory] = useState({});
  const [loading, setLoading] = useState(false);
  // Pass a function to Form (child) component to get field data
  const [form, setForm] = useState();
  // 0: both closed, 1: open add window, 2: open edit window
  const [modalStatus, setModalStatus] = useState(0);

  const fetchCategories = async () => {
    setLoading(true);
    // All visibale (non-deleted) items;
    setCategories(await reqVisibleExerciseCategories());
    setLoading(false);
  };

  useEffect(() => {
    fetchCategories();
  }, []);

  const extra = (
    <Button
      type="primary"
      onClick={() => {
        setModalStatus(1);
      }}
    >
      <PlusOutlined /> Add
    </Button>
  );

  const deleteCategory = (id) => {
    const deleteReq = async (id) => {
      try {
        const response = await reqDeleteExerciseCategory(id);
        console.log("Delete category resopnse: ", response);
        fetchCategories();
      } catch (err) {
        message.error(err.message || err);
      }
    };
    Modal.confirm({
      title: "Do you want to delete this category?",
      icon: <ExclamationCircleOutlined />,
      onOk() {
        deleteReq(id);
      },
      onCancel() {},
    });
  };

  const showUpdate = (category) => {
    setCategory(category);
    setModalStatus(2);
  };

  const addCategory = async () => {
    try {
      const { name, desc } = await form.validateFields();
      const response = await reqAddExerciseCategory(name, desc);
      console.log("Add category response: ", response);
      setModalStatus(0);
      form.resetFields();
      setTimeout(() => {
        message.success("New category created");
        // Update table
        fetchCategories();
      }, 1000);
    } catch (err) {
      message.error("Error adding category: ", err);
    }
  };

  const editCategory = async () => {
    try {
      const { name, desc, avatar } = await form.validateFields();
      const response = await reqUpdateExerciseCategory(category.id, name, desc, avatar);
      console.log("Update category response: ", response);
      setModalStatus(0);
      form.resetFields();
      setTimeout(() => {
        message.success("Category updated");
        fetchCategories();
      }, 1000);
    } catch (err) {
      message.error("Error editing category: ", err);
    }
  };

  const handleCancel = () => {
    form.resetFields();
    setModalStatus(0);
  };

  return (
    <>
      <Card title={title} extra={extra} loading={loading}>
        <div className="card-container">
          {categories.map((category) => (
            <Card
              className="card"
              size="small"
              hoverable={true}
              key={category.id}
              cover={
                <Image height={90} width={90} src={category.avatar} alt="category" />
              }
              actions={[
                <EditOutlined
                  key="edit"
                  onClick={() => {
                    showUpdate(category);
                  }}
                />,
                <DeleteOutlined
                  key="delete"
                  onClick={() => deleteCategory(category.id)}
                />,
                <Tooltip title={category.desc} placement="topRight">
                  <EllipsisOutlined key="ellipsis" />
                </Tooltip>,
              ]}
            >
              <Meta title={category.name} />
            </Card>
          ))}
        </div>
      </Card>

      <Modal
        title="Add category"
        open={modalStatus === 1}
        onOk={addCategory}
        onCancel={handleCancel}
      >
        <AddForm setForm={setForm} />
      </Modal>
      <Modal
        title="Edit category"
        open={modalStatus === 2}
        onOk={editCategory}
        onCancel={handleCancel}
      >
        <UpdateForm
          categoryName={category.name}
          categoryDesc={category.desc}
          categoryAvatar={category.avatar}
          setForm={setForm}
        />
      </Modal>
    </>
  );

}

export default Category;
