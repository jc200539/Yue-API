import { Modal } from 'antd';
import React from 'react';
import { ProColumns, ProTable } from '@ant-design/pro-components';
import '@umijs/max';

export type Props = {
  columns: ProColumns<API.InterfaceInfo>[];
  onCancel: () => void;
  onSubmit: (values: API.InterfaceInfo) => Promise<boolean>;
  open: boolean;
};

const CreateModal: React.FC<Props> = (props) => {
  const { columns, open, onCancel } = props;
  return (
    <Modal open={open} onCancel={() => onCancel?.()}>
      <ProTable columns={columns} type={'form'} />
    </Modal>
  );
};

export default CreateModal;
